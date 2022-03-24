package me.maxiiiiii.skyblockdragons.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.Functions.setLore;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.playerGoBack;

public class GoBack implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().getType() == InventoryType.CHEST) {
            Player player = (Player) e.getPlayer();
            Inventory inventory = e.getInventory();
            ArrayList<Inventory> defaultValue = new ArrayList<>();
            ArrayList<Inventory> inventories = playerGoBack.getOrDefault(player.getUniqueId(), defaultValue);

            try {
                if (!inventories.get(inventories.size() - 1).getTitle().contains(inventory.getTitle().split(" ")[0]) && !inventory.getTitle().contains("Bank")) {
                    inventories.add(inventory);
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                inventories.add(inventory);
            }

            playerGoBack.put(player.getUniqueId(), inventories);

            for (int i = 0; i < e.getInventory().getSize(); i++) {
                try {
                    if (e.getInventory().getItem(i).getItemMeta().getDisplayName().contains("Go Back") && inventories.size() > 1) {
                        setLore(e.getInventory().getItem(i), new ArrayList<>(Arrays.asList(ChatColor.GRAY + "To " + ChatColor.stripColor(inventories.get(inventories.size() - 2).getTitle()))));
                    }
                } catch (NullPointerException ignored) {}
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

        if (e.getClickedInventory().getType() == InventoryType.CHEST) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back")) {
                Player player = (Player) e.getWhoClicked();
                ArrayList<Inventory> inventories = playerGoBack.get(player.getUniqueId());
                player.closeInventory();
                if (inventories.size() > 1) {
                    inventories.remove(inventories.size() - 1);
                    player.openInventory(inventories.get(inventories.size() - 1));
                    playerGoBack.put(player.getUniqueId(), inventories);
                }
            }
        }
    }
}
