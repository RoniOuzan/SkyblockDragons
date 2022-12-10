package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBreakBlock(BlockBreakEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Material brokeMaterial = e.getBlock().getType();
        BlockMaterial blockMaterial = BlockMaterial.get(player.getWorldSD(), brokeMaterial, e.getBlock().getData());
        if (!player.getWorld().getName().contains("ASkyBlock") && player.getGameMode() != GameMode.CREATIVE && blockMaterial == null){
            SkyblockDragons.logger.info(String.format("Player %s NOT Broke Block %s at %s", player.getName(), brokeMaterial, e.getBlock().getLocation()));
            player.sendMessage(ChatColor.RED + "You can't break this block! [UNKNOWN]");
            e.setCancelled(true);
        } else {
            SkyblockDragons.logger.info(String.format("Player %s Broke Block %s at %s", player.getName(), blockMaterial, e.getBlock().getLocation()));
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (!player.getWorld().getName().contains("ASkyBlock") && player.getGameMode() != GameMode.CREATIVE){
            event.setCancelled(true);
        }
    }

//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onPlayerBreakBlockTracker(BlockBreakEvent event) {
//        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
//        if (player == null)
//            return;
//        String msg = "BreakBlockTracker: " + String.format("block: %s ", event.getBlock()) +
//                String.format("cancelled? %s", event.isCancelled());
//        player.getLogger().info(msg);
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onPlayerPlaceBlockTracker(BlockPlaceEvent event) {
//        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
//        if (player == null)
//            return;
//        String msg = "BreakBlockTracker: " + String.format("block: %s ", event.getBlock()) +
//                String.format("cancelled? %s", event.isCancelled());
//        player.getLogger().info(msg);
//    }
}
