package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Aspect_of_The_Void implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Functions.getId(item).equals("ASPECT_OF_THE_VOID")) {
                Player player = e.getPlayer();

                if (!player.isSneaking()) {
                    if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
                    Functions.teleportForward(player, 12);
                } else {
                    Block b = player.getTargetBlock(null, 61);
                    Block b1 = b.getLocation().add(0, 1, 0).getBlock();
                    Block b2 = b.getLocation().add(0, 2, 0).getBlock();
                    float yaw = player.getLocation().getYaw();
                    float pitch = player.getLocation().getPitch();
                    Location l = b1.getLocation();
                    l.add(0.5, 0, 0.5);
                    l.setYaw(yaw);
                    l.setPitch(pitch);
                    if (b.getType() != Material.AIR) {
                        if (b1.getType() == Material.AIR && b2.getType() == Material.AIR) {
                            if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 1)) return;
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
                            player.teleport(l);
                            b1.getWorld().spawnParticle(Particle.PORTAL, l, 500, 0.1, 0.1, 0.1);
                        } else {
                            player.sendMessage(ChatColor.RED + "There is a block there!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "No blocks found!");
                    }
                }
            }
        }
    }
}
