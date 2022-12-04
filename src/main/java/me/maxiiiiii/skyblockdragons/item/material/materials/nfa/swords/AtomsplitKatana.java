package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.*;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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

    public static class Soulcry extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown, Listener {
        public Soulcry() {
            super(AbilityAction.RIGHT_CLICK,
                    "Soulcry",
                    "Gain " + ChatColor.RED + "+400" + StatTypes.FEROCITY + ChatColor.GRAY + " against Endermen for " + ChatColor.GREEN + "4 seconds" + ChatColor.GRAY + "."
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
                Functions.Wait(1, () -> SkyblockDragons.players.get(player.getUniqueId()).applyStats(false));

                Functions.Wait(80, () -> {
                    for (int i = 0; i < 36; i++) {
                        try {
                            if (Functions.getId(player.getInventory().getItem(i)).equals("ATOMSPLIT_KATANA")) {
                                player.getInventory().getItem(i).setType(Material.DIAMOND_SWORD);
                            }
                        } catch (NullPointerException ignored) {}
                    }
                });
            }
        }

        @EventHandler
        public void updateStats(UpdateStatsEvent e) {
            if (e.isNotThisItem(Items.get("ATOMSPLIT_KATANA"))) return;

            if (SkyblockDragons.getCurrentTimeInSeconds() - this.getLastTimeUsed(e.getPlayer()) <= 4) {
                e.getStats().add(StatTypes.FEROCITY, 400, new StatAdd<>(StatAddType.ITEM_ABILITY, this));
            }
        }
    }
}
