package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.menus.SkyblockMenu;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (Functions.getId(item).equals("SKYBLOCK_MENU")) {
            e.setCancelled(true);
            new SkyblockMenu(SkyblockDragons.getPlayer(e.getPlayer()));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getInventory().getType() != InventoryType.PLAYER) return;

        if (Functions.getId(e.getCurrentItem()).equals("SKYBLOCK_MENU")) {
            e.getWhoClicked().closeInventory();
            new SkyblockMenu(SkyblockDragons.getPlayer((Player) e.getWhoClicked()));
        }
    }
}
