package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class LeapingSword extends SwordMaterial {
    public LeapingSword() {
        super("LEAPING_SWORD",
                Material.GOLD_SWORD,
                ItemFamily.LEAPING_SWORD,
                "Leaping Sword",
                Rarity.EPIC,
                new Stats(150, 100, 25, 0, 0, 0, 0, 0, 0, 0),
                "",
                new Leap()
        );
    }

    public static class Leap extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        private final HashMap<UUID, Boolean> isEnabled = new HashMap<>();

        public Leap() {
            super(AbilityAction.RIGHT_CLICK,
                    "Leap",
                    "Leap into the air and deal " + ChatColor.GREEN + "400 " + ChatColor.GRAY + "base Magic Damage to nearby NEW_LINE enemies upon landing on the NEW_LINE ground. Damaged enemies will NEW_LINE also be frozen for " + ChatColor.GREEN + "1 second" + ChatColor.GRAY + "."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 50;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 1;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Location l = player.getLocation();
                Vector v = new Vector(l.getDirection().getX() * 5, l.getDirection().getY() / 3 + 1, l.getDirection().getZ() * 5);
                player.setVelocity(v);

                if (!isEnabled.getOrDefault(player.getUniqueId(), false)) {
                    isEnabled.put(player.getUniqueId(), true);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                    player.spawnParticle(Particle.EXPLOSION_LARGE, player.getLocation(), 1, 0, 0, 0, 2);
                                    player.spawnParticle(Particle.LAVA, player.getLocation(), 15, 1, 0.5, 1, 0);
                                    for (int i = 0; i < 5; i++) {
                                        FallingBlock fb = player.getWorld().spawnFallingBlock(player.getLocation(), new MaterialData(Material.WEB));
                                        int x;
                                        if (Math.random() > 0.5) {
                                            x = 2;
                                        } else {
                                            x = -2;
                                        }
                                        int z;
                                        if (Math.random() > 0.5) {
                                            z = 2;
                                        } else {
                                            z = -2;
                                        }
                                        fb.setVelocity(new Vector(Math.random() / x, 0.2, Math.random() / z));
                                        fb.setCustomName("LeapingSword_" + i);
                                        fb.setDropItem(false);
                                    }
                                    isEnabled.put(player.getUniqueId(), false);
                                    cancel();
                                }, 2L);
                            }
                        }
                    }.runTaskTimer(plugin, 20L, 1L);
                }
            };
        }
    }
}
