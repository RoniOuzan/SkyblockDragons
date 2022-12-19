package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (Functions.getId(e.getItemDrop().getItemStack()).equals("SKYBLOCK_MENU")) e.setCancelled(true);
    }

//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onPlayerDropItemTracker(PlayerDropItemEvent event) {
//        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
//        if (player == null) {
//            return;
//        }
//        String msg = "DropItemTracker: " + String.format("Item: %s ", event.getItemDrop()) +
//                String.format("cancelled? %s", event.isCancelled());
//        player.getLogger().info(msg);
//    }
}
