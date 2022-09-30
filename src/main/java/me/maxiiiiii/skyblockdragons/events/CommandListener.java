package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCommandTracker(PlayerCommandPreprocessEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player == null) {
            return;
        }
        String msg = "PlayerCommandTracker: " + String.format("Command: %s ", event.getMessage()) +
                String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }
}
