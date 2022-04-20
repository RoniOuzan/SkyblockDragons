package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.objects.SlotCooldown;
import me.maxiiiiii.skyblockdragons.util.objects.aifly;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class Bonemerang implements Listener {
    private final int AMOUNT = 12;
    private final int ROTATE = 40;
    private final SlotCooldown cooldown = new SlotCooldown();

    private void updateItem(Player player, int slot, ItemStack item) {
        cooldown.setCooldown(player + ":" + slot, System.currentTimeMillis() - 10000);
        for (int a = 0; a < 36; a++) {
            try {
                if (player.getInventory().getItem(a).isSimilar(item)) {
                    player.getInventory().getItem(a).setType(Material.BONE);
                }
            } catch (NullPointerException ignored) {}
        }
    }

    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("BONEMERANG")) return;

        Player player = e.getPlayer();
        Location location = player.getLocation().add(0, 0.5, 0);

        int slot = player.getInventory().getHeldItemSlot();
        if (Functions.slotCooldown(player, slot, cooldown, 10000, false)) return;

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
//                    new BukkitRunnable() {
//                        int i = AMOUNT;
//                        @Override
//                        public void run() {
//                            if (stand.isDead()) cancel();
//                            if (i <= 0) {
//                                stand.remove();
//                                updateItem(player, slot, item);
//                                cancel();
//                            }
//                            Location newLocation = location.clone().add(location.getDirection().multiply(i));
//                            if (chanceOf(20)) location.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, newLocation.clone().add(0, 1, 0), 0, 0, 0, 0, 0);
//                            stand.teleport(newLocation);
//
//                            if (stand.getLocation().clone().add(0, 1, 0).getBlock().getType().isSolid()) {
//                                stand.getWorld().spawnParticle(Particle.CLOUD, stand.getLocation(), 5, 1, 1, 1, 1);
//                                stand.remove();
//                                new BukkitRunnable() {
//                                    @Override
//                                    public void run() {
//                                        updateItem(player, slot, item);
//                                    }
//                                }.runTaskLater(plugin, 60L);
//                                player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1f, 20f);
//                                cancel();
//                            }
//
//                            i--;
//                        }
//                    }.runTaskTimer(plugin, 0L, 1L);
                    new aifly(stand, player, AMOUNT * 50).runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
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
}
