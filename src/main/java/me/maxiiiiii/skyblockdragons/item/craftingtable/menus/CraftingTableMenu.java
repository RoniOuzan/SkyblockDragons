package me.maxiiiiii.skyblockdragons.item.craftingtable.menus;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.createItem;
import static me.maxiiiiii.skyblockdragons.util.Functions.numToSlot;

public class CraftingTableMenu {
    public static ItemStack recipe = createItem(Material.BARRIER, ChatColor.RED + "Recipe Required", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Add the items for valid recipe", ChatColor.GRAY + "in the crafting grid to the", ChatColor.GRAY + "left!")));
    public static ItemStack quickCraft = createItem(Material.STAINED_GLASS_PANE, 1, 7, ChatColor.RED + "Quick Crafting Slot", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Quick crafting allows you to", ChatColor.GRAY + "craft items without assembling", ChatColor.GRAY + "the recipe.")));

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

        inventory.setItem(23, recipe);

        inventory.setItem(16, quickCraft);
        inventory.setItem(25, quickCraft);
        inventory.setItem(34, quickCraft);

        List<Recipe> recipes = Recipe.getRecipesCanCraft(SkyblockDragons.getPlayer(player), 3);
        if (recipes.size() > 0)
            inventory.setItem(16, Functions.setUnstackable(recipes.get(0).getItem()));
        if (recipes.size() > 1)
            inventory.setItem(25, Functions.setUnstackable(recipes.get(1).getItem()));
        if (recipes.size() > 2)
            inventory.setItem(34, Functions.setUnstackable(recipes.get(2).getItem()));

        player.openInventory(inventory);
    }

    public static void updateLines(Inventory inventory, boolean red) {
        for (int i = 45; i < 54; i++) {
            if (i == 49) continue;

            if (red)
                inventory.setItem(i, Functions.createItem(Material.STAINED_GLASS_PANE, 14, ChatColor.RESET + ""));
            else
                inventory.setItem(i, Functions.createItem(Material.STAINED_GLASS_PANE, 5, ChatColor.RESET + ""));
        }
    }
}

/*
public CraftingTableMenu(PlayerSD player) {
        super(player, "Craft Item", 6, InventoryGlassType.ALL, false);

        this.setItem(23, createItem(Material.BARRIER, ChatColor.RED + "Recipe Required", "RECIPE_REQUIRED", ChatColor.GRAY + "Add the items for valid recipe", ChatColor.GRAY + "in the crafting grid to the", ChatColor.GRAY + "left!"));

        for (int i = 0; i < 9; i++) {
            this.setItem(Functions.numToSlot(i), new ItemStack(Material.AIR));
        }
    }

    @Override
    public void update() {
        for (int i = 45; i < 54; i++) {
            this.setItem(i, Functions.createItem(Material.STAINED_GLASS_PANE, 14, ChatColor.RESET + ""));
        }

        this.setItem(16, createItem(Material.STAINED_GLASS_PANE, 7, ChatColor.RED + "Quick Crafting Slot", "QUICK_CRAFT", ChatColor.GRAY + "Quick crafting allows you to", ChatColor.GRAY + "craft items without assembling", ChatColor.GRAY + "the recipe."));
        this.setItem(25, createItem(Material.STAINED_GLASS_PANE, 7, ChatColor.RED + "Quick Crafting Slot", "QUICK_CRAFT", ChatColor.GRAY + "Quick crafting allows you to", ChatColor.GRAY + "craft items without assembling", ChatColor.GRAY + "the recipe."));
        this.setItem(34, createItem(Material.STAINED_GLASS_PANE, 7, ChatColor.RED + "Quick Crafting Slot", "QUICK_CRAFT", ChatColor.GRAY + "Quick crafting allows you to", ChatColor.GRAY + "craft items without assembling", ChatColor.GRAY + "the recipe."));

        List<Recipe> recipes = Recipe.getRecipesCanCraft(SkyblockDragons.getPlayer(player), 3);
        if (recipes.size() > 0)
            inventory.setItem(16, Functions.setUnstackable(recipes.get(0).getItem()));
        if (recipes.size() > 1)
            inventory.setItem(25, Functions.setUnstackable(recipes.get(1).getItem()));
        if (recipes.size() > 2)
            inventory.setItem(34, Functions.setUnstackable(recipes.get(2).getItem()));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if ((e.getSlot() % 9 == 1 || e.getSlot() % 9 == 2 || e.getSlot() % 9 == 3) && (e.getSlot() / 9 == 1 || e.getSlot() / 9 == 2 || e.getSlot() / 9 == 3)) {
            e.setCancelled(false);
        }
    }
 */
