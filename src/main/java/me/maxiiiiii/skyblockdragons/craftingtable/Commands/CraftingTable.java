package me.maxiiiiii.skyblockdragons.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.itemcreator.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.itemcreator.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.SkinMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static me.maxiiiiii.skyblockdragons.util.Functions.getHotPotato;

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
            Player player = (Player) e.getWhoClicked();

            new BukkitRunnable() {
                @Override
                public void run() {
                    checkRecipe(player);
                }
            }.runTaskLater(SkyblockDragons.plugin, 1L);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("Craft Item")) {
            Player player = (Player) e.getWhoClicked();
            if (e.getSlot() != 10 && e.getSlot() != 11 && e.getSlot() != 12 && e.getSlot() != 19 && e.getSlot() != 20 && e.getSlot() != 21 && e.getSlot() != 28 && e.getSlot() != 29 && e.getSlot() != 30 && e.getClickedInventory().getTitle().contains("Craft Item")) {
                e.setCancelled(true);
            }

            if (e.getSlot() == 23 && e.getCurrentItem().getType() != Material.BARRIER && !e.getCurrentItem().getItemMeta().getDisplayName().contains("Recipe Required")) {
                if (isEnoughItems(player)) {
                    Recipe recipe = Recipe.valueOf(Functions.getItemMaterial(e.getCurrentItem()).name());

                    ItemStack itemToUpgrade = e.getInventory().getItem(Functions.numToSlot(recipe.getSlotToUpgrade()));

                    int hotPotato;
                    ReforgeType reforge;
                    boolean recombabulated;
                    SkinMaterial skin;
                    Map<EnchantType, Short> enchants;

                    try {
                        hotPotato = getHotPotato(itemToUpgrade);
                        reforge = Functions.getReforge(itemToUpgrade);
                        recombabulated = Functions.isRecombed(itemToUpgrade);
                        skin = Functions.getSkin(itemToUpgrade);
                        enchants = Functions.getEnchants(itemToUpgrade);
                    } catch (NullPointerException ex) {
                        hotPotato = 0;
                        reforge = ReforgeType.NULL;
                        recombabulated = false;
                        skin = SkinMaterial.NULL;
                        enchants = new HashMap<>();
                    }
                    if (e.getClick().isShiftClick()) {
                        takeItem(player, recipe);

                        player.getInventory().addItem(new Item(recipe.getItem().getMaterial(), recipe.getItem().getAmount(), hotPotato, reforge, recombabulated, skin, enchants));
                        InventoryClickEvent event = new InventoryClickEvent(player.getOpenInventory(), InventoryType.SlotType.CONTAINER, 23, ClickType.SHIFT_LEFT, InventoryAction.NOTHING);
                        Bukkit.getServer().getPluginManager().callEvent(event);
                    } else if (e.getClick().isLeftClick()) {
                        takeItem(player, recipe);

                        player.setItemOnCursor(new Item(recipe.getItem().getMaterial(), recipe.getItem().getAmount(), hotPotato, reforge, recombabulated, skin, enchants));
                    }
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.getOpenInventory().getTitle().contains("Craft Item"))
                        checkRecipe(player);
                }
            }.runTaskLater(SkyblockDragons.plugin, 1L);
        }
    }

    private static void checkRecipe(Player player) {
        boolean putBack = true;
        Item[] items = new Item[9];
        for (int i = 0; i < 9; i++) {
            try {
                items[i] = new Item(Functions.getItemMaterial(player.getOpenInventory().getItem(Functions.numToSlot(i))), player.getOpenInventory().getItem(Functions.numToSlot(i)).getAmount());
            } catch (NullPointerException ex) {
                items[i] = new Item(ItemMaterial.NULL, 0);
            }
        }
        for (Recipe recipe : Recipe.values()) {
            int amount = 0;
            for (int i = 0; i < 9; i++) {
                Item item1 = items[i];
                Item item2;

                try {
                    item2 = recipe.getItems()[i];
                } catch (NullPointerException ex) {
                    item2 = new Item(ItemMaterial.NULL, 0);
                }

                try {
                    if (item1.getMaterial().name().equals(item2.getMaterial().name()) && item1.getAmount() >= item2.getAmount())
                        amount++;
                } catch (NullPointerException ex) {
                    amount++;
                }
            }
            if (amount == 9) {
                ItemStack itemToUpgrade = player.getOpenInventory().getItem(Functions.numToSlot(recipe.getSlotToUpgrade()));

                int hotPotato;
                ReforgeType reforge;
                boolean recombabulated;
                SkinMaterial skin;
                Map<EnchantType, Short> enchants;

                try {
                    hotPotato = Functions.getHotPotato(itemToUpgrade);
                    reforge = Functions.getReforge(itemToUpgrade);
                    recombabulated = Functions.isRecombed(itemToUpgrade);
                    skin = Functions.getSkin(itemToUpgrade);
                    enchants = Functions.getEnchants(itemToUpgrade);
                } catch (NullPointerException ex) {
                    hotPotato = 0;
                    reforge = ReforgeType.NULL;
                    recombabulated = false;
                    skin = SkinMaterial.NULL;
                    enchants = new HashMap<>();
                }

                player.getOpenInventory().setItem(23, new Item(recipe.getItem().getMaterial(), recipe.getItem().getAmount(), hotPotato, reforge, recombabulated, skin, enchants));

                putBack = false;
                updateLine(player, false);
            }
        }
        if (putBack) {
            ItemStack item = Functions.createItem(Material.BARRIER, ChatColor.RED + "Recipe Required", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Add the items for valid recipe", ChatColor.GRAY + "in the crafting grid to the", ChatColor.GRAY + "left!")));
            player.getOpenInventory().setItem(23, item);
            updateLine(player, true);
        }
    }

    private static void takeItem(Player player, Recipe recipe) {
        for (int i = 0; i < 9; i++) {
            try {
                player.getOpenInventory().setItem(Functions.numToSlot(i), removeAmount(player.getOpenInventory().getItem(Functions.numToSlot(i)), recipe.getItems()[i].getAmount()));
            } catch (NullPointerException ignored) {}
        }
    }

    private static void updateLine(Player player, boolean red) {
        for (int i = 45; i < 54; i++) {
            if (i == 49) continue;
            ItemStack glass = Functions.createItem(Material.STAINED_GLASS_PANE, 5,ChatColor.RESET + "");
            if (red) {
                glass = Functions.createItem(Material.STAINED_GLASS_PANE, 14,ChatColor.RESET + "");
            }
            player.getOpenInventory().setItem(i, glass);
        }
    }

    private static ItemStack removeAmount(ItemStack itemStack, int amount) {
        ItemStack item = itemStack.clone();
        if (itemStack.getAmount() > amount) {
            item.setAmount(itemStack.getAmount() - amount);
        } else {
            item = new ItemStack(Material.AIR);
        }
        return item;
    }

    private static boolean isEnoughItems(Player player) {
        Item[] items = new Item[9];
        for (int i = 0; i < 9; i++) {
            try {
                items[i] = new Item(Functions.getItemMaterial(player.getOpenInventory().getItem(Functions.numToSlot(i))), player.getOpenInventory().getItem(Functions.numToSlot(i)).getAmount());
            } catch (NullPointerException ex) {
                items[i] = new Item(ItemMaterial.NULL, 0);
            }
        }
        for (Recipe recipe : Recipe.values()) {
            int amount = 0;
            for (int i = 0; i < 9; i++) {
                Item item1 = items[i];
                Item item2;

                try {
                    item2 = recipe.getItems()[i];
                } catch (NullPointerException ex) {
                    item2 = new Item(ItemMaterial.NULL, 0);
                }

                try {
                    if (item1.getMaterial().name().equals(item2.getMaterial().name()) && item1.getAmount() >= item2.getAmount())
                        amount++;
                } catch (NullPointerException ex) {
                    amount++;
                }
            }
            if (amount == 9) {
                return true;
            }
        }
        return false;
    }
}
