package me.maxiiiiii.skyblockdragons.item.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class CraftingTable implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            CraftingTableMenu.openCraftingTable(player);
        }
        return true;
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getInventory().getTitle().contains("Craft Item")) {
            Functions.Wait(1L, () -> updateInventory((Player) e.getWhoClicked()));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getInventory().getTitle().contains("Craft Item")) Functions.Wait(1L, () -> updateInventory((Player) e.getWhoClicked()));

        if (!e.getClickedInventory().getTitle().contains("Craft Item")) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());
        if ((e.getSlot() % 9 < 1 || e.getSlot() % 9 > 3) || (e.getSlot() / 9 == 0 || e.getSlot() / 9 > 3)) {
            e.setCancelled(true);
        }

        if (e.getClick() == ClickType.DOUBLE_CLICK && e.getInventory().getItem(23).isSimilar(CraftingTableMenu.recipe)) e.setCancelled(true);

        if (e.getSlot() == 23) {
            if (!e.getCurrentItem().isSimilar(CraftingTableMenu.recipe)) {
                Recipe recipe = getRecipe(e.getInventory());
                if (e.getClick().isShiftClick()) {
                    e.getInventory().setItem(23, CraftingTableMenu.recipe);
                    player.getInventory().addItem(e.getCurrentItem());

                    removeItems(e.getInventory(), recipe);

                    updateInventory(player);

                    InventoryClickEvent event = new InventoryClickEvent(player.getOpenInventory(), InventoryType.SlotType.CONTAINER, 23, ClickType.SHIFT_LEFT, InventoryAction.NOTHING);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                } else {
                    if (Functions.isNotAir(e.getCursor())) {
                        if (e.getCurrentItem().isSimilar(e.getCursor()) && e.getCursor().getAmount() + e.getCurrentItem().getAmount() <= e.getCurrentItem().getType().getMaxStackSize()) {
                            e.getCursor().setAmount(e.getCursor().getAmount() + e.getCurrentItem().getAmount());
                            player.setItemOnCursor(e.getCursor());
                            removeItems(e.getInventory(), recipe);
                        }
                    } else {
                        player.setItemOnCursor(e.getCurrentItem());
                        removeItems(e.getInventory(), recipe);
                    }
                }
            }
        }
        if (e.getSlot() == 16 || e.getSlot() == 25 || e.getSlot() == 34) {
            if (!e.getCurrentItem().isSimilar(CraftingTableMenu.quickCraft)) {
                int row = e.getSlot() / 9;

                Recipe recipe = Recipe.getRecipesCanCraft(player, 3).get(row - 1);

                if (e.getClick().isShiftClick()) {
                    player.getInventory().addItem(e.getCurrentItem());

                    player.removeItems(recipe.getAllItems());
                } else {
                    if (Functions.isNotAir(e.getCursor())) {
                        if (e.getCurrentItem().isSimilar(e.getCursor()) && e.getCursor().getAmount() + e.getCurrentItem().getAmount() <= e.getCurrentItem().getType().getMaxStackSize()) {
                            e.getCursor().setAmount(e.getCursor().getAmount() + e.getCurrentItem().getAmount());
                            player.setItemOnCursor(e.getCursor());

                            player.removeItems(recipe.getAllItems());
                        }
                    } else {
                        player.setItemOnCursor(e.getCurrentItem());

                        player.removeItems(recipe.getAllItems());
                    }
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
            CraftingTableMenu.updateLines(inventory, true);
            inventory.setItem(23, CraftingTableMenu.recipe);
        } else {
            if (recipe.getItem() instanceof Item)
                if (recipe.getSlotToUpgrade() >= 0)
                    inventory.setItem(23, new Item(((Item) recipe.getItem()).getMaterial(), inventory.getItem(Functions.numToSlot(recipe.getSlotToUpgrade()))));
                else
                    inventory.setItem(23, recipe.getItem());
            CraftingTableMenu.updateLines(inventory, false);
        }
        List<Recipe> recipes = Recipe.getRecipesCanCraft(SkyblockDragons.getPlayer(player), 3);
        if (recipes.size() > 0)
            inventory.setItem(16, Functions.setUnstackable(recipes.get(0).getItem()));
        else
            inventory.setItem(16, CraftingTableMenu.quickCraft);
        if (recipes.size() > 1)
            inventory.setItem(25, Functions.setUnstackable(recipes.get(1).getItem()));
        else
            inventory.setItem(16, CraftingTableMenu.quickCraft);
        if (recipes.size() > 2)
            inventory.setItem(34, Functions.setUnstackable(recipes.get(2).getItem()));
        else
            inventory.setItem(16, CraftingTableMenu.quickCraft);
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
}
