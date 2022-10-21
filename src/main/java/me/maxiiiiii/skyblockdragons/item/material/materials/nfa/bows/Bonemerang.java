package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.bows;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.types.RangeWeaponMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilitySilentCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.AIFly;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;
import java.util.Map;

public class Bonemerang extends RangeWeaponMaterial {
    public Bonemerang() {
        super("BONEMERANG",
                Material.BONE,
                ItemFamily.BONEMERANG,
                "Bonemerang",
                ItemType.RANGE_WEAPON,
                Rarity.LEGENDARY,
                new Stats(270, 130, 0, 0, 0, 0, 0, 0, 0, 0),
                "Deals " + ChatColor.RED + "double damage " + ChatColor.GRAY + "when coming back. Pierces up to " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "foes.",
                new Swing()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Swing extends ItemAbility implements ItemAbilitySilentCooldown {
        public Swing() {
            super(AbilityAction.RIGHT_CLICK,
                    "Swing",
                    "Throw the bone a short distance, dealing the damage an arrow would."
            );
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return ((SwingRunnable) this.getAbilityOfPlayer(player).getRunnable()).isOnCooldown.getOrDefault(player.getInventory().getHeldItemSlot(), false) ? Double.MAX_VALUE : 0;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new SwingRunnable();
        }

        private static class SwingRunnable implements PlayerAbilityRunnable {
            private final int AMOUNT = 12;
            private final int ROTATE = 40;

            private Map<Integer, Boolean> isOnCooldown = new HashMap<>();

            @Override
            public void run(PlayerAbilityUsage e) {
                Item item = e.getItem();

                Player player = e.getPlayer();
                Location location = player.getLocation().add(0, 0.5, 0);

                int slot = player.getInventory().getHeldItemSlot();
                if (isOnCooldown.getOrDefault(player.getInventory().getHeldItemSlot(), false)) return;
                isOnCooldown.put(player.getInventory().getHeldItemSlot(), true);

                player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 0.5f, 12f);

                ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

                stand.setVisible(false);
                stand.setGravity(false);
                stand.setMarker(true);
                stand.addScoreboardTag("Bonemerang");

                ItemStack bone = new ItemStack(Material.BONE);
                stand.setItemInHand(bone);

                item.setType(Material.GHAST_TEAR);

                new BukkitRunnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        if (stand.isDead()) cancel();
                        if (i > AMOUNT) {
                            new AIFly(stand, player, AMOUNT * 25).runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
                            new BukkitRunnable() {
                                int i = 0;
                                @Override
                                public void run() {
                                    if (i > AMOUNT) {
                                        stand.remove();
                                        updateItem(player, slot, item);
                                        cancel();
                                    }
                                    if (stand.getLocation().clone().add(0, 1, 0).getBlock().getType().isSolid()) {
                                        stand.getWorld().spawnParticle(Particle.CLOUD, stand.getLocation(), 5, 1, 1, 1, 1);
                                        stand.remove();
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                updateItem(player, slot, item);
                                            }
                                        }.runTaskLater(SkyblockDragons.plugin, 60L);
                                        player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1f, 20f);
                                        cancel();
                                    }
                                    i++;
                                }
                            }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
                            cancel();
                        }

                        Location newLocation = location.clone().add(location.getDirection().multiply(i * 15 / AMOUNT));
                        stand.teleport(newLocation);

                        if (stand.getLocation().clone().add(0, 1, 0).getBlock().getType().isSolid()) {
                            stand.getWorld().spawnParticle(Particle.CLOUD, stand.getLocation(), 5, 1, 1, 1, 1);
                            stand.remove();
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    updateItem(player, slot, item);
                                }
                            }.runTaskLater(SkyblockDragons.plugin, 60L);
                            player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1f, 20f);
                            cancel();
                        }

                        i++;
                    }
                }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);

                new BukkitRunnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        if (stand.isDead()) cancel();
                        if (i >= AMOUNT * 2) cancel();
                        stand.setRightArmPose(new EulerAngle(Math.toRadians(-10), Math.toRadians(ROTATE * i), 0));
                        i++;
                    }
                }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
            }

            private void updateItem(Player player, int slot, ItemStack item) {
                isOnCooldown.put(slot, true);
                for (int i = 0; i < 36; i++) {
                    try {
                        if (player.getInventory().getItem(i).isSimilar(item)) {
                            player.getInventory().getItem(i).setType(Material.BONE);
                        }
                    } catch (NullPointerException ignored) {}
                }
            }
        }
    }
}
