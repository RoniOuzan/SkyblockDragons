package me.maxiiiiii.skyblockdragons.item.reforge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class ReforgeMenu {
    public static void openReforgeMenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, ChatColor.DARK_GRAY + "Reforge Menu");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        for (int i = 0; i < 45; i++) {
            inv.setItem(i, glass);
        }

        ItemStack redGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        int n = 0;
        for (int i = 0; i < 5; i++) {
            inv.setItem(n, redGlass);
            n += 9;
        }
        n = 8;
        for (int i = 0; i < 5; i++) {
            inv.setItem(n, redGlass);
            n += 9;
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        setName(close, ChatColor.RED + "Close");
        setLore(close, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inv.setItem(40, close);

        inv.setItem(13, new ItemStack(Material.AIR));

        ItemStack anvil = new ItemStack(Material.ANVIL);
        setName(anvil, ChatColor.GREEN + "Reforge Item");
        setLore(anvil, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Place an item above to reforge", ChatColor.GRAY + "it! Reforging items adds a", ChatColor.GRAY + "random modifier to the item that", ChatColor.GRAY + "grants stats boosts.", "", ChatColor.YELLOW + "Click to reforge!")));
        inv.setItem(22, anvil);

        player.openInventory(inv);
    }
}
