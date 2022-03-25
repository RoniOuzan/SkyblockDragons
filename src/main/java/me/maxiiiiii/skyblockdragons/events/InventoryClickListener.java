package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
}
