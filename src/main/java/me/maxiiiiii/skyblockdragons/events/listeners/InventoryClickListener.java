package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

import java.util.Arrays;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (Functions.getId(e.getCurrentItem()).equals("SKYBLOCK_MENU") && e.getSlot() == 8) {
            e.setCancelled(true);
        }

        if (e.getClick() == ClickType.NUMBER_KEY && e.getHotbarButton() == 8) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClickTracker(InventoryClickEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getWhoClicked().getUniqueId());
        if (player == null) {
            return;
        }
        String msg = "ClickTracker: " + String.format("InvType=%s ", event.getInventory().getType()) +
                String.format("type=%s ", event.getClick()) +
                String.format("action=%s ", event.getAction()) +
                String.format("slot=%s ", event.getSlot()) +
                String.format("rawSlot=%s ", event.getRawSlot()) +
                String.format("clickedInvName=%s ", event.getView().getTitle()) +
                String.format("clickedItem=%s ", event.getCurrentItem()) + String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryOpenTracker(InventoryOpenEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer().getUniqueId());
        if (player == null) {
            return;
        }
        String msg = "OpenTracker: " + String.format("InvType=%s ", event.getInventory().getType()) +
                String.format("name=%s ", event.getInventory().getName()) +
                String.format("inventory=%s ", Arrays.toString(event.getInventory().getContents())) +
                String.format("location=%s ", event.getInventory().getLocation()) +
                String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryCloseTracker(InventoryCloseEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer().getUniqueId());
        if (player == null) {
            return;
        }
        String msg = "CloseTracker: " + String.format("InvType=%s ", event.getInventory().getType()) +
                String.format("name=%s ", event.getInventory().getName()) +
                String.format("inventory=%s ", Arrays.toString(event.getInventory().getContents())) +
                String.format("location=%s ", event.getInventory().getLocation());
        player.getLogger().info(msg);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryDragTracker(InventoryDragEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getWhoClicked().getUniqueId());
        if (player == null) {
            return;
        }
        String msg = "DragTracker: " + String.format("InvType=%s ", event.getInventory().getType()) +
                String.format("name=%s ", event.getInventory().getName()) +
                String.format("inventory=%s ", Arrays.toString(event.getInventory().getContents())) +
                String.format("location=%s ", event.getInventory().getLocation()) +
                String.format("slots=%s ", event.getInventorySlots()) +
                String.format("DragType=%s ", event.getType()) +
                String.format("OldCursor=%s ", event.getOldCursor()) +
                String.format("Cursor=%s ", event.getCursor()) +
                String.format("NewItems=%s ", event.getNewItems()) +
                String.format("cancelled? %s", event.isCancelled());
        player.getLogger().info(msg);
    }
}
