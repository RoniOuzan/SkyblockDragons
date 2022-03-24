package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (Functions.getId(e.getCurrentItem()).equals("SKYBLOCK_MENU") && e.getSlot() == 8) {
            e.setCancelled(true);
        } else if (Functions.getId(e.getCurrentItem()).equals("SKYBLOCK_MENU")) {
            e.getInventory().setItem(8, null);
        }

        if (e.getClick() == ClickType.NUMBER_KEY && e.getHotbarButton() == 8) {
            e.setCancelled(true);
        }
    }
}
