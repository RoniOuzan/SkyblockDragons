package me.maxiiiiii.skyblockdragons.craftingtable.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.Functions.*;

public class CraftingTable {
    public static void openCraftingTable(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Craft Item");

        ItemStack glass = createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack redGlass = createItem(Material.STAINED_GLASS_PANE, 14, ChatColor.RESET + "");
        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, redGlass);
        }

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(49, goBack);

        for (int i = 0; i < 9; i++) {
            inventory.setItem(numToSlot(i), new ItemStack(Material.AIR));
        }

        ItemStack recipe = createItem(Material.BARRIER, ChatColor.RED + "Recipe Required", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Add the items for valid recipe", ChatColor.GRAY + "in the crafting grid to the", ChatColor.GRAY + "left!")));
        inventory.setItem(23, recipe);

        ItemStack quickCraft = createItem(Material.STAINED_GLASS_PANE, 7, ChatColor.RED + "Quick Crafting Slot", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Quick crafting allows you to", ChatColor.GRAY + "craft items without assembling", ChatColor.GRAY + "the recipe.")));
        inventory.setItem(16, quickCraft);
        inventory.setItem(25, quickCraft);
        inventory.setItem(34, quickCraft);

        player.openInventory(inventory);
    }
}
