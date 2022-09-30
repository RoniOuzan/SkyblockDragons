package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.mining.PlayerBreakBlockEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerBreakBlockTracker(BlockBreakEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player == null)
            return;
        String msg = "BreakBlockTracker: " + String.format("block: %s ", event.getBlock()) +
                String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerBreakBlockTracker(BlockPlaceEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player == null)
            return;
        String msg = "BreakBlockTracker: " + String.format("block: %s ", event.getBlock()) +
                String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }
}
