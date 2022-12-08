package me.maxiiiiii.skyblockdragons.world.listeners;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.events.PlayerWarpEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerWarpListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerWarp(PlayerWarpEvent e) {
        PlayerSD player = e.getPlayer();
        player.visitNewWorld(e.getWarp().getWorld());

        player.teleport(e.getWarp().getLocation());
        player.setBedSpawnLocation(e.getWarp().getLocation(), true);
        player.sendMessage(ChatColor.GREEN + "Warped to " + e.getWarp().getName());
    }
}
