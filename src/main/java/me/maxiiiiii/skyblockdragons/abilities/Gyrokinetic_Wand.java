package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Gyrokinetic_Wand implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("GYROKINETIC_WAND")) return;
        if (e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
        if (Functions.cooldown(player, cooldown, 5000, true)) return;

        Block b = player.getTargetBlock(null, 128);
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i >= 60) {
                    Bukkit.getScheduler().runTaskLater(SkyblockDragons.plugin, () -> {
                        for (Entity entity : Functions.loopEntities(b.getLocation(), 4)) {
                            entity.setVelocity(new Vector(0, 2, 0));
                        }
                    }, 1L);
                    cancel();
                }
                int size;
                int size2;
                int size3;
                if (i <= 12) {
                    size = 10;
                    size2 = 9;
                    size3 = 8;
                } else if (i <= 24) {
                    size = 8;
                    size2 = 7;
                    size3 = 6;
                } else if (i <= 36) {
                    size = 6;
                    size2 = 5;
                    size3 = 4;
                } else if (i <= 48) {
                    size = 4;
                    size2 = 3;
                    size3 = 2;
                } else {
                    size = 3;
                    size2 = 2;
                    size3 = 1;
                }
                for (Block value2 : Functions.loopBlocksScopeHor(b.getLocation(), size, size2, size3)) {
                    Block value = value2;
                    for (int i1 = 0; i1 < 10; i1++) {
                        if (value2.getLocation().clone().add(0, i1 + 1, 0).getBlock().getType() == Material.AIR) {
                            if (value2.getLocation().add(0, i1, 0).getBlock().getType() != Material.AIR) {
                                value = value2.getLocation().add(0, i1, 0).getBlock();
                                break;
                            }
                        }
                    }
                    if (value.getLocation().getY() == value2.getLocation().getY()) {
                        for (int i1 = 0; i1 < 10; i1++) {
                            if (value2.getLocation().clone().add(0, -i1, 0).getBlock().getType() != Material.AIR) {
                                value = value2.getLocation().clone().add(0, -i1, 0).getBlock();
                                break;
                            }
                        }
                    }
                    if (Functions.randomInt(0, 100) <= 20) {
                        value.getWorld().spawnParticle(Particle.SPELL_WITCH, value.getLocation().clone().add(0, 0.5, 0), 1);
                        int n = Functions.randomInt(1, 6);
                        double a = Functions.randomDouble(2, 3);
                        a /= 10;
                        MaterialData materialData;
                        if (n <= 4) {
                            materialData = new MaterialData(Material.OBSIDIAN);
                        } else if (n == 5) {
                            materialData = new MaterialData(Material.WOOL);
                            materialData.setData(Byte.parseByte("2"));
                        } else {
                            materialData = new MaterialData(Material.WOOL);
                            materialData.setData(Byte.parseByte("10"));
                        }
                        FallingBlock fb = value.getWorld().spawnFallingBlock(value.getLocation().clone().add(0, a, 0), materialData);
                        fb.setDropItem(false);
                        fb.addScoreboardTag("GyrokineticWand");
                    }
                }
                for (Entity entity : Functions.loopEntitiesScope(b.getLocation(), size, size2, size3)) {
                    if (entity instanceof Creature) {
                        entity.setVelocity(new Vector((b.getX() - entity.getLocation().getX()) / 5, (b.getY() - entity.getLocation().getY()) / 10 - 0.1, (b.getZ() - entity.getLocation().getZ()) / 5));
                    }
                }
                i++;
            }
        }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    @EventHandler
    public void onLand(EntityChangeBlockEvent e) {
        if (e.getEntity().getScoreboardTags().contains("GyrokineticWand")) {
            e.setCancelled(true);
        }
    }
}
