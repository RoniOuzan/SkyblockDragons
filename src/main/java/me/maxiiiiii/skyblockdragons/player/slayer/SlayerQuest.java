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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@Getter
public class SlayerQuest implements Listener {
    public enum SlayerQuestState {
        STARTED, SPAWNED, SLAYED, COMPLETED, FAILED
    }

    private final PlayerSD player;
    private final SlayerType type;
    private final int tier;
    private double currentXp;
    private final double needXp;
    private SlayerQuestState state;
    private EntitySD boss;
    private boolean isAutoSlayer;

    public SlayerQuest(PlayerSD player, SlayerType type, int tier) {
        this.player = player;
        this.type = type;
        this.tier = tier;
        this.currentXp = 0;
        this.needXp = this.type.getNeedXp(this.tier);
        this.state = SlayerQuestState.STARTED;
        this.boss = null;
        this.isAutoSlayer = false;

        Bukkit.getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public void setAutoSlayer(boolean autoSlayer) {
        this.isAutoSlayer = autoSlayer;
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
        if (this.boss != null) this.boss.remove();
    }

    public void spawn(Location location) {
        this.state = SlayerQuestState.SPAWNED;
        this.boss = new EntitySD(location, EntityMaterial.get(this.type.getBossId() + "_TIER_" + this.tier));

        Bukkit.getPluginManager().callEvent(new SlayerBossSpawnEvent(this.player, this.boss));
    }

    public void reset() {
        this.state = SlayerQuestState.STARTED;
        this.currentXp = 0;

        if (this.boss != null) this.boss.remove();
    }

    public void giveReward() {
        this.player.getSlayers().giveXp(this.type, this.type.getXpReward(this.tier));
    }

    public void slayed() {
        if (this.isAutoSlayer()) {
            this.reset();
            this.giveReward();
        } else {
            this.state = SlayerQuestState.SLAYED;
            this.player.sendMessage("  " + ChatColor.GREEN + "" + ChatColor.BOLD + "NICE! SLAYER BOSS SLAIN");
            this.player.sendMessage("   " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "⇨ " + ChatColor.GRAY + "Talk to Maddox to claim your " + this.type.toString() + " Slayer XP!");
            this.player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }

        Bukkit.getPluginManager().callEvent(new PlayerCompleteSlayerQuestEvent(this.player));
    }

    public void complete() {
        this.state = SlayerQuestState.COMPLETED;
        this.player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        int level = this.player.getSlayers().get(this.type).getLevel();
        this.giveReward();
        this.player.sendMessage("  " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "SLAYER QUEST COMPLETE!");
        if (this.player.getSlayers().get(this.type).getLevel() > level) {
            this.player.sendMessage("   " + ChatColor.GREEN + "" + ChatColor.BOLD + "LVL UP! " + ChatColor.DARK_PURPLE + "⇨ " + ChatColor.YELLOW + this.type + " Slayer LVL " + this.player.getSlayers().get(this.type).getLevel());
        } else {
            this.player.sendMessage("   " + ChatColor.YELLOW + this.type + " Slayer LVL " + this.player.getSlayers().get(this.type).getLevel() + ChatColor.GRAY + " - Next LVL in " + ChatColor.AQUA + (this.getNeedXp() - this.getCurrentXp()));
        }

        Bukkit.getPluginManager().callEvent(new PlayerCompleteSlayerQuestEvent(this.player));
    }

    public void fail() {
        this.state = SlayerQuestState.FAILED;
        this.player.sendMessage("  " + ChatColor.RED + "SLAYER QUEST FAILED!");
        this.player.sendMessage("   " + ChatColor.GRAY + "Talk to Maddox to start another slayer quest!");

        Bukkit.getPluginManager().callEvent(new PlayerFailSlayerQuestEvent(this.player));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBossDeath(EntityDeathEvent e) {
        if (e.getEntity() == this.boss)
            this.slayed();
            Bukkit.getPluginManager().callEvent(new SlayerBossSlayedEvent(this.player,
                    this.boss,
                    e.getKiller() instanceof PlayerSD ? (PlayerSD) e.getKiller() : this.player)
            );
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getPlayer() == this.player) {
            this.fail();
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetSkillXP(PlayerGetSkillXpEvent e) {
        if (e.getSkillType() == SkillType.COMBAT && e.getSource().getType() == SkillXpSourceType.ENTITY) { // TODO: make it only entityType specific
            this.giveXp(e.getFinalAmount(), (EntitySD) e.getSource().getSource());
            this.player.getScoreboardSD().update();
        }
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
