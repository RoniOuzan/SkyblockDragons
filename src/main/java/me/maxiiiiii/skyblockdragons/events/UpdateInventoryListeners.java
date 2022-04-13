package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class UpdateInventoryListeners implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getType() == InventoryType.CHEST) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) e.getPlayer());
            player.updatePlayerInventory();
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        player.updatePlayerInventory();
    }
}