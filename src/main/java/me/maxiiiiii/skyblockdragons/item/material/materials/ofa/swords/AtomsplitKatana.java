package me.maxiiiiii.skyblockdragons.item.material.materials.ofa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

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

    @Override
    public void updateStats(PlayerStats stats) {
        if (System.currentTimeMillis() - Soulcry.atomsplitAbility.getOrDefault(stats.getPlayer(), 0L) <= 4000) {
            stats.add(StatType.FEROCITY, 400);
        }
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Soulcry extends ItemAbility {
        private static final HashMap<Player, Long> atomsplitAbility = new HashMap<>();

        public Soulcry() {
            super(AbilityAction.RIGHT_CLICK,
                    "Soulcry",
                    "Gain " + ChatColor.RED + "+400" + StatType.FEROCITY.getIconAndText() + ChatColor.GRAY + " against Endermen for " + ChatColor.GREEN + "4 seconds" + ChatColor.GRAY + ".",
                    200,
                    false,
                    4
            );
        }

        @Override
        public Runnable onAbilityUse(PlayerUseAbilityEvent e) {
            return () -> {
                PlayerSD player = e.getPlayer();

                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1f, 1f);
                player.getEquipment().getItemInMainHand().setType(Material.GOLD_SWORD);
                atomsplitAbility.put(player, System.currentTimeMillis());

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
            };
        }
    }
}
