package me.maxiiiiii.skyblockdragons.world.warp;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerWarpListener implements Listener {
    @EventHandler
    public void onPlayerWarp(PlayerWarpEvent e) {
        e.getPlayer().teleport(e.getWarp().getLocation());
        e.getPlayer().sendMessage(ChatColor.GREEN + "Warped to " + e.getWarp().getName());
    }
}
