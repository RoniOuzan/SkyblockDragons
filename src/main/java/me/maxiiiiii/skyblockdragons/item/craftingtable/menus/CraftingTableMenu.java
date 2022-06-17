package me.maxiiiiii.skyblockdragons.item.craftingtable.menus;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.BlockColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CraftingTableMenu extends Menu {
    public ItemStack recipeRequired = createItem(Material.BARRIER, ChatColor.RED + "Recipe Required", "RECIPE", ChatColor.GRAY + "Add the items for valid recipe", ChatColor.GRAY + "in the crafting grid to the", ChatColor.GRAY + "left!");
    public ItemStack quickCraft = createItem(Material.STAINED_GLASS_PANE, 7, ChatColor.RED + "Quick Craft Slot", "QUICK_CRAFT", ChatColor.GRAY + "Quick crafting allows you to", ChatColor.GRAY + "craft items without assembling", ChatColor.GRAY + "the recipe.");
    
    public CraftingTableMenu(PlayerSD player) {
        super(player, "Craft Item", 6, InventoryGlassType.ALL, false);
    }

    @Override
    public void update() {
        this.updateLines(true);

        for (int i = 0; i < 9; i++) {
            this.setItem(Functions.numToSlot(i), new ItemStack(Material.AIR));
        }

        this.setItem(23, recipeRequired);

        this.setItem(16, quickCraft);
        this.setItem(16, quickCraft);
        this.setItem(16, quickCraft);
    }
    
    public void updateLines(boolean red) {
        if (red) {
            for (int i = 45; i < 54; i++) {
                if (i == 49) continue;
                this.setItem(i, getGlass(BlockColor.RED));
            }
        } else {
            for (int i = 45; i < 54; i++) {
                if (i == 49) continue;
                this.setItem(i, getGlass(BlockColor.LIME));
            }
        }
    }

    @Override
    public void onInventoryDrag(InventoryDragEvent e) {
        Functions.Wait(1L, () -> updateInventory(player));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        Functions.Wait(1L, () -> updateInventory((Player) e.getWhoClicked()));

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());
        if ((e.getSlot() % 9 == 1 || e.getSlot() % 9 == 2 || e.getSlot() % 9 == 3) && (e.getSlot() / 9 == 1 || e.getSlot() / 9 == 2 || e.getSlot() / 9 == 3))
            e.setCancelled(false);

        if (e.getSlot() == 23) {
            if (this.getNBT(e.getCurrentItem()).equals("RESULT")) {
                Recipe recipe = getRecipe(e.getInventory());
                if (e.getClick().isShiftClick()) {
                    e.getInventory().setItem(23, recipeRequired);
                    player.getInventory().addItem(recipe.getItem());

                    removeItems(e.getInventory(), recipe);

                    updateInventory(player);

                    InventoryClickEvent event = new InventoryClickEvent(player.getOpenInventory(), InventoryType.SlotType.CONTAINER, 23, ClickType.SHIFT_LEFT, InventoryAction.NOTHING);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                } else {
                    if (Functions.isNotAir(e.getCursor())) {
                        if (recipe.getItem().isSimilar(e.getCursor()) && e.getCursor().getAmount() + recipe.getItem().getAmount() <= recipe.getItem().getType().getMaxStackSize()) {
                            e.getCursor().setAmount(e.getCursor().getAmount() + recipe.getItem().getAmount());
                            player.setItemOnCursor(e.getCursor());
                            removeItems(e.getInventory(), recipe);
                        }
                    } else {
                        player.setItemOnCursor(recipe.getItem());
                        removeItems(e.getInventory(), recipe);
                    }
                }
            }
        }
        if (this.getNBT(e.getCurrentItem()).equals("QUICK_CRAFT_RESULT")) {
            int row = e.getSlot() / 9;

            Recipe recipe = Recipe.getRecipesCanCraft(player, 3).get(row - 1);

            if (e.getClick().isShiftClick()) {
                player.getInventory().addItem(recipe.getItem());

                player.removeItems(recipe.getAllItems());
            } else {
                if (Functions.isNotAir(e.getCursor())) {
                    if (recipe.getItem().isSimilar(e.getCursor()) && e.getCursor().getAmount() + recipe.getItem().getAmount() <= recipe.getItem().getType().getMaxStackSize()) {
                        e.getCursor().setAmount(e.getCursor().getAmount() + recipe.getItem().getAmount());
                        player.setItemOnCursor(e.getCursor());

                        player.removeItems(recipe.getAllItems());
                    }
                } else {
                    player.setItemOnCursor(recipe.getItem());

                    player.removeItems(recipe.getAllItems());
                }
            }
        }

        Functions.Wait(1L, () -> updateInventory(player));
    }

    public void removeItems(Inventory inventory, Recipe recipe) {
        for (int i = 0; i < 9; i++) {
            int slot = Functions.numToSlot(i);

            if (!Functions.isNotAir(inventory.getItem(slot))) continue;

            ItemStack item = inventory.getItem(slot).clone();
            item.setAmount(item.getAmount() - recipe.getItems()[i].getAmount());
            inventory.setItem(slot, item);
        }
    }

    public void updateInventory(Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        Recipe recipe = getRecipe(inventory);

        if (recipe == null) {
            updateLines(true);
            inventory.setItem(23, recipeRequired);
        } else {
            if (recipe.getItem() instanceof Item)
                if (recipe.getSlotToUpgrade() >= 0) {
                    ItemStack item = new Item(SkyblockDragons.getPlayer(player), ((Item) recipe.getItem()).getMaterial(), inventory.getItem(Functions.numToSlot(recipe.getSlotToUpgrade())));
                    inventory.setItem(23, addNBT(item, "RESULT"));
                } else {
                    ItemStack item = recipe.getItem();
                    inventory.setItem(23, addNBT(item, "RESULT"));
                }
            updateLines(false);
        }
        List<Recipe> recipes = Recipe.getRecipesCanCraft(SkyblockDragons.getPlayer(player), 3);
        if (recipes.size() > 0)
            inventory.setItem(16, addNBT(recipes.get(0).getItem(), "QUICK_SLOT_RESULT"));
        else
            inventory.setItem(16, quickCraft);
        if (recipes.size() > 1)
            inventory.setItem(25, addNBT(recipes.get(1).getItem(), "QUICK_SLOT_RESULT"));
        else
            inventory.setItem(16, quickCraft);
        if (recipes.size() > 2)
            inventory.setItem(34, addNBT(recipes.get(2).getItem(), "QUICK_SLOT_RESULT"));
        else
            inventory.setItem(16, quickCraft);
    }

    public Recipe getRecipe(Inventory inventory) {
        Item[] items = new Item[9];
        for (int i = 0; i < 9; i++) {
            if (Functions.isNotAir(inventory.getItem(Functions.numToSlot(i))))
                items[i] = new Item(inventory.getItem(Functions.numToSlot(i)));
            else
                items[i] = null;
        }
        for (Recipe recipe : Recipe.recipes.values()) {
            if (compareArrays(items, recipe.getItems()))
                return recipe;
        }
        return null;
    }

    public boolean compareArrays(ItemStack[] inventory, ItemStack[] required) {
        if (inventory.length != required.length) return false;

        int amount = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null ^ required[i] == null) {
                break;
            }

            if ((inventory[i] == null && required[i] == null) || (Functions.getId(inventory[i]).equals(Functions.getId(required[i])) && inventory[i].getAmount() >= required[i].getAmount())) {
                amount++;
            }
        }
        return amount == inventory.length;
    }

    public boolean compareArraysShapeless(Item[] inventory, Item[] required) {
        if (inventory.length != required.length) return false;

        List<Item> inv = Arrays.stream(inventory).filter(Objects::nonNull).collect(Collectors.toList());
        List<Item> req = Arrays.stream(required).filter(Objects::nonNull).collect(Collectors.toList());

        if (inv.size() != req.size()) return false;

        int amount = 0;
        for (Item item : inv) {
            for (int j = 0; j < req.size(); j++) {
                if (item.getMaterial() == req.get(j).getMaterial() && item.getAmount() >= req.get(j).getAmount()) {
                    req.remove(j);
                    amount++;
                    break;
                }
            }
        }
        return amount == 9;
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            new CraftingTableMenu(player);
        }

        @Override
        public List<Argument> tabComplete(List<Argument> tabs) {
            return tabs;
        }
    }
}

/*
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
 */

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
