package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerDeathEvent;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.skill.SkillXpSourceType;
import me.maxiiiiii.skyblockdragons.player.skill.events.PlayerGetSkillXpEvent;
import me.maxiiiiii.skyblockdragons.player.slayer.events.PlayerCompleteSlayerQuestEvent;
import me.maxiiiiii.skyblockdragons.player.slayer.events.PlayerFailSlayerQuestEvent;
import me.maxiiiiii.skyblockdragons.player.slayer.events.SlayerBossSlayedEvent;
import me.maxiiiiii.skyblockdragons.player.slayer.events.SlayerBossSpawnEvent;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@Getter
public class SlayerQuest implements Listener {
    public enum SlayerQuestState {
        NOT_ACTIVE, STARTED, SPAWNED, SLAIN, FAILED
    }

    private final PlayerSD player;
    private boolean isAutoSlayer;
    private SlayerType type;
    private int tier;
    private double currentXp;
    private double needXp;
    private SlayerQuestState state;
    private EntitySD boss;

    public SlayerQuest(PlayerSD player) {
        this.player = player;
        this.isAutoSlayer = Variables.getBoolean(player.getUniqueId(), "Slayer", "autoslayer", false);
        this.type = null;
        this.tier = 0;
        this.currentXp = -1;
        this.needXp = 0;
        this.state = SlayerQuestState.NOT_ACTIVE;
        this.boss = null;

        Bukkit.getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public void start(SlayerType slayerType, int tier) {
        this.type = slayerType;
        this.tier = tier;
        this.currentXp = 0;
        this.needXp = this.type.getQuestNeedXp(this.tier);
        this.state = SlayerQuestState.STARTED;

        if (this.boss != null) {
            this.boss.remove();
            this.boss = null;
        }

        player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  SLAYER QUEST STARTED!");
        player.sendMessage(ChatColor.DARK_PURPLE + "   » " + ChatColor.GRAY + "Slay " + ChatColor.RED + slayerType.getQuestNeedXp(tier) + " Combat XP " + ChatColor.GRAY + "worth of Zombies.");
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_AMBIENT, 1f, 2f);
    }

    public void reset() {
        this.start(this.type, this.tier);
    }

    public boolean isActive() {
        return this.state != SlayerQuestState.NOT_ACTIVE;
    }

    public void toggleAutoSlayer() {
        this.isAutoSlayer = !this.isAutoSlayer;
    }

    public boolean isBossSpawned() {
        return this.state == SlayerQuestState.SPAWNED;
    }

    public void giveXp(double amount, EntitySD source) {
        this.currentXp += amount;

        if (this.currentXp >= this.needXp && !this.isBossSpawned()) {
            this.spawn(source.getLocation());
        }
    }

    public void cancel() {
        this.state = SlayerQuestState.NOT_ACTIVE;
        if (this.boss != null) this.boss.remove();
    }

    public void spawn(Location location) {
        this.state = SlayerQuestState.SPAWNED;

        this.playSpawnParticles(location);
        this.player.playSound(Sound.ITEM_FLINTANDSTEEL_USE, 0.8);
        Functions.Wait(2, () -> {
            this.player.playSound(Sound.ITEM_FLINTANDSTEEL_USE, 0.8);
            Functions.Loop(6, 1,
                    i1 -> this.playSpawnParticles(location)
                    , i1 -> Functions.Loop(4, 3, i2 -> {
                        playSpawnParticles(location);
                        playSpawnSounds();
                    }, i2 -> {
                        playSpawnParticles(location);
                        Functions.Wait(2, () -> {
                            playSpawnParticles(location);
                            Functions.Loop(4, 3, i -> {
                                playSpawnParticles(location);
                                playSpawnSounds();
                            }, i -> {
                                playSpawnParticles(location);
                                this.player.playSound(Sound.ENTITY_GENERIC_EXPLODE, 0.8, 0);
                                Functions.Wait(2, () -> {
                                    this.player.playSound(Sound.ENTITY_WITHER_SPAWN, 0.8);
                                    this.boss = new EntitySD(location, EntityMaterial.get(this.type.getBossId() + "_TIER_" + this.tier));

                                    Bukkit.getPluginManager().callEvent(new SlayerBossSpawnEvent(this.player, this.boss));
                                });
                            });
                        });
                    })
            );
        });
    }

    private void playSpawnParticles(Location location) {
        this.player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, location.clone().add(0, 0.5, 0), 150, 0.5, 1, 0.5, 0.2);
        this.player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, location.clone().add(0, 1, 0), 10, 0.2, 0.2, 0.2, 0);
        this.player.getWorld().spawnParticle(Particle.SPELL_WITCH, location.clone().add(0, 1, 0), 50, 0.3, 0.3, 0.3, 1);
    }

    private void playSpawnSounds() {
        this.player.playSound(Sound.ENTITY_WITHER_SHOOT, 0.4, 4.8);
        this.player.playSound(Sound.ENTITY_ARROW_SHOOT, 0.05, 100);
        this.player.playSound(Sound.ENTITY_ARROW_SHOOT, 0.05, 100);
    }

    public double getReward() {
        return this.player.getSlayers().get(this.type).getRewardXP(this.tier);
    }

    public void giveReward() {
        this.player.getSlayers().get(this.type).giveXp(this.getReward());
    }

    public void slain() {
        if (this.isAutoSlayer()) {
            this.reset();
            this.complete();
        } else {
            this.state = SlayerQuestState.SLAIN;
            this.player.sendMessage("  " + ChatColor.GREEN + "" + ChatColor.BOLD + "NICE! SLAYER BOSS SLAIN");
            this.player.sendMessage("   " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "⇨ " + ChatColor.GRAY + "Talk to Maddox to claim your " + this.type.toString() + " Slayer XP!");
            this.player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }

        Bukkit.getPluginManager().callEvent(new PlayerCompleteSlayerQuestEvent(this.player));
    }

    public void complete() {
        this.state = this.isAutoSlayer() ? SlayerQuestState.STARTED : SlayerQuestState.NOT_ACTIVE;
        this.player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        int level = this.player.getSlayers().get(this.type).getLevel();
        this.giveReward();
        this.player.sendMessage("  " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "SLAYER QUEST COMPLETE!");
        if (this.player.getSlayers().get(this.type).getLevel() > level) {
            this.player.sendMessage("   " + ChatColor.GREEN + "" + ChatColor.BOLD + "LVL UP! " + ChatColor.DARK_PURPLE + "⇨ " + ChatColor.YELLOW + this.type + " Slayer LVL " + this.player.getSlayers().get(this.type).getLevel());
        } else {
            this.player.sendMessage("   " + ChatColor.YELLOW + this.type + " Slayer LVL " + this.player.getSlayers().get(this.type).getLevel() + ChatColor.GRAY + " - Next LVL in " + ChatColor.AQUA + Functions.getNumberFormat(this.player.getSlayers().get(this.type).getNeedXP() - this.player.getSlayers().get(this.type).getTotalXp()));
        }
        this.player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        Functions.Wait(5L, () -> this.player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f));

        Bukkit.getPluginManager().callEvent(new PlayerCompleteSlayerQuestEvent(this.player));
    }

    public void fail() {
        this.state = SlayerQuestState.FAILED;
        this.player.sendMessage("  " + ChatColor.RED + "" + ChatColor.BOLD + "SLAYER QUEST FAILED!");
        this.player.sendMessage("   " + ChatColor.GRAY + "Talk to Maddox to start another slayer quest!");

        Bukkit.getPluginManager().callEvent(new PlayerFailSlayerQuestEvent(this.player));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBossDeath(EntityDeathEvent e) {
        if (e.getEntity() == this.boss)
            this.slain();
            Bukkit.getPluginManager().callEvent(new SlayerBossSlayedEvent(this.player,
                    this.boss,
                    e.getKiller() instanceof PlayerSD ? (PlayerSD) e.getKiller() : this.player)
            );
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getPlayer() == this.player && this.isBossSpawned()) {
            this.fail();
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetSkillXP(PlayerGetSkillXpEvent e) {
        if (e.getSkillType() == SkillType.COMBAT && e.getSource().getType() == SkillXpSourceType.ENTITY && this.getPlayer() == e.getPlayer() && this.isActive()) { // TODO: make it only entityType specific
            this.giveXp(e.getFinalAmount(), (EntitySD) e.getSource().getSource());
            this.player.getScoreboardSD().update();
        }
    }

    public void save() {
        Variables.set(player.getUniqueId(), "Slayer", "autoslayer", this.isAutoSlayer);
    }

    @Override
    public String toString() {
        return "SlayerQuest{" +
                "player=" + player +
                ", type=" + type +
                ", tier=" + tier +
                ", currentXp=" + currentXp +
                ", needXp=" + needXp +
                ", state=" + state +
                ", boss=" + boss +
                '}';
    }
}
