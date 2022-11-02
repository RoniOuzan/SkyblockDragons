package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.events.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

public class AtomsplitKatana extends SwordMaterial {
    public AtomsplitKatana() {
        super("ATOMSPLIT_KATANA",
                Material.DIAMOND_SWORD,
                ItemFamily.VOIDWALKER_KATANA,
                "Atomsplit Katana",
                Rarity.LEGENDARY,
                new Stats(240, 100, 25, 0, 0, 0, 0, 0, 0, 300),
                "Deal " + ChatColor.GREEN + "+200% RESET_LENGTH " + ChatColor.GRAY + "damage to Endermen.",
                new Soulcry()
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.isNotThisItem(this)) return;
        
        if (SkyblockDragons.getCurrentTimeInSeconds() - ((Soulcry) this.getAbilities().get(0)).getLastTimeUsed(e.getPlayer()) <= 4) {
            e.getStats().add(StatType.FEROCITY, 400);
        }
    }

    public static class Soulcry extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        public Soulcry() {
            super(AbilityAction.RIGHT_CLICK,
                    "Soulcry",
                    "Gain " + ChatColor.RED + "+400" + StatType.FEROCITY.getIconAndText() + ChatColor.GRAY + " against Endermen for " + ChatColor.GREEN + "4 seconds" + ChatColor.GRAY + "."
            );
        }

        public double getLastTimeUsed(PlayerSD player) {
            return ((SoulcryRunnable) this.getAbilityOfPlayer(player).getRunnable()).lastTimeUsed;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 200;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 4;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new SoulcryRunnable();
        }

        public static class SoulcryRunnable implements PlayerAbilityRunnable {
            private double lastTimeUsed = 0;

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();

                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1f, 1f);
                player.getEquipment().getItemInMainHand().setType(Material.GOLD_SWORD);
                lastTimeUsed = SkyblockDragons.getCurrentTimeInSeconds();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        SkyblockDragons.players.get(player.getUniqueId()).applyStats(false);
                    }
                }.runTaskLater(SkyblockDragons.plugin, 1L);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            try {
                                if (Functions.getId(player.getInventory().getItem(i)).equals("ATOMSPLIT_KATANA")) {
                                    player.getInventory().getItem(i).setType(Material.DIAMOND_SWORD);
                                }
                            } catch (NullPointerException ignored) {}
                        }
                    }
                }.runTaskLater(SkyblockDragons.plugin, 80L);
            }
        }
    }
}
