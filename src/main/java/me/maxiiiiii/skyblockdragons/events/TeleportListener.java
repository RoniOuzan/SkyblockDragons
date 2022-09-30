package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerTeleportTracker(PlayerTeleportEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player == null) {
            return;
        }
        String msg = "TeleportTracker: " + String.format("From: %s ", event.getFrom()) +
                String.format("To: %s ", event.getTo()) +
                String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangedWorldTracker(PlayerChangedWorldEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player == null) {
            return;
        }
        String msg = "ChangedWorldTracker: " + String.format("From: %s ", event.getFrom()) +
                String.format("To: %s ", event.getPlayer().getWorld());
        player.getLogger().info(msg);
    }
}
