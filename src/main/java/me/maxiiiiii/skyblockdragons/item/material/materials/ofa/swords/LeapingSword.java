package me.maxiiiiii.skyblockdragons.item.material.materials.ofa.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
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

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Leap extends ItemAbility {
        private final HashMap<UUID, Boolean> isEnabled = new HashMap<>();

        public Leap() {
            super(AbilityAction.RIGHT_CLICK,
                    "Leap",
                    "Leap into the air and deal " + ChatColor.GREEN + "400 " + ChatColor.GRAY + "base Magic Damage to nearby NEW_LINE enemies upon landing on the NEW_LINE ground. Damaged enemies will NEW_LINE also be frozen for " + ChatColor.GREEN + "1 second" + ChatColor.GRAY + ".",
                    50,
                    false,
                    1
            );
        }

        @Override
        public Runnable onAbilityUse(PlayerUseAbilityEvent e) {
            return () -> {
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
