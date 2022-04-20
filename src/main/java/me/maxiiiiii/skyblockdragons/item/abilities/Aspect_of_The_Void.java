package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Aspect_of_The_Void implements Listener {
    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("ASPECT_OF_THE_VOID")) return;

        PlayerSD player = e.getPlayer();

        if (!player.isSneaking()) {
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
