package me.maxiiiiii.skyblockdragons.commands.menu;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellMenu {
    public static void openSellMenu(PlayerSD player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "Sell Menu");

        ItemStack glass = Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(49, close);

        player.openInventory(inventory);
    }
}
