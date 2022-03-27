package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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

import java.util.HashMap;
import java.util.UUID;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class Leaping_Sword implements Listener {
    private final HashMap<UUID, Boolean> isEnabled = new HashMap<>();
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("LEAPING_SWORD")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        if (players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
        if (cooldown(player, cooldown, 1000, true)) return;

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
    }

    @EventHandler
    public void onFallingBlockLand(EntityChangeBlockEvent e) {
        if (e.getEntity().getName().contains("LeapingSword_")) {
            e.setCancelled(true);
        }
    }
}
