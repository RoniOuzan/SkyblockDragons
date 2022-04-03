package me.maxiiiiii.skyblockdragons.item.craftingtable.commands;

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
            Functions.Wait(1L, () -> updateInventory(e.getInventory()));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getInventory().getTitle().contains("Craft Item")) Functions.Wait(1L, () -> updateInventory(e.getInventory()));

        if (!e.getClickedInventory().getTitle().contains("Craft Item")) return;

        Player player = (Player) e.getWhoClicked();
        if ((e.getSlot() % 9 < 1 || e.getSlot() % 9 > 3) || (e.getSlot() / 9 == 0 || e.getSlot() / 9 > 3)) {
            e.setCancelled(true);
        }

        if (e.getClick() == ClickType.DOUBLE_CLICK && e.getInventory().getItem(23).isSimilar(CraftingTableMenu.recipe)) e.setCancelled(true);

        if (e.getSlot() == 23) {
            if (!e.getCurrentItem().isSimilar(CraftingTableMenu.recipe)) {
                Recipe recipe = getRecipe(e.getInventory());
                if (e.getClick().isShiftClick()) {
                    e.getInventory().setItem(23, CraftingTableMenu.recipe);
                    player.getInventory().addItem(recipe.getItem());

                    removeItems(e.getInventory(), recipe);

                    updateInventory(e.getInventory());

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

        Functions.Wait(1L, () -> updateInventory(e.getClickedInventory()));
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

    public void updateInventory(Inventory inventory) {
        Recipe recipe = getRecipe(inventory);

        if (recipe == null) {
            CraftingTableMenu.updateLines(inventory, true);
            inventory.setItem(23, CraftingTableMenu.recipe);
        } else {
            inventory.setItem(23, recipe.getItem());
            CraftingTableMenu.updateLines(inventory, false);
        }
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
//            if (e.getSlot() != 10 && e.getSlot() != 11 && e.getSlot() != 12 && e.getSlot() != 19 && e.getSlot() != 20 && e.getSlot() != 21 && e.getSlot() != 28 && e.getSlot() != 29 && e.getSlot() != 30 && e.getClickedInventory().getTitle().contains("Craft Item")) {
//                e.setCancelled(true);
//            }
//
//            if (e.getSlot() == 23 && e.getCurrentItem().getType() != Material.BARRIER && !e.getCurrentItem().getItemMeta().getDisplayName().contains("Recipe Required")) {
//                if (isEnoughItems(player)) {
//                    Recipe recipe = Recipe.get(Functions.getItemMaterial(e.getCurrentItem()).name());
//
//                    ItemStack itemToUpgrade = e.getInventory().getItem(Functions.numToSlot(recipe.getSlotToUpgrade()));
//
//                    int hotPotato;
//                    ReforgeType reforge;
//                    boolean recombabulated;
//                    SkinMaterial skin;
//                    Map<EnchantType, Short> enchants;
//
//                    try {
//                        hotPotato = getHotPotato(itemToUpgrade);
//                        reforge = Functions.getReforge(itemToUpgrade);
//                        recombabulated = Functions.isRecombed(itemToUpgrade);
//                        skin = Functions.getSkin(itemToUpgrade);
//                        enchants = Functions.getEnchants(itemToUpgrade);
//                    } catch (NullPointerException ex) {
//                        hotPotato = 0;
//                        reforge = ReforgeType.NULL;
//                        recombabulated = false;
//                        skin = SkinMaterial.NULL;
//                        enchants = new HashMap<>();
//                    }
//                    if (e.getClick().isShiftClick()) {
//                        takeItem(player, recipe);
//
//                        player.getInventory().addItem(new Item(recipe.getItem().getMaterial(), recipe.getItem().getAmount(), hotPotato, reforge, recombabulated, skin, enchants));
//                        InventoryClickEvent event = new InventoryClickEvent(player.getOpenInventory(), InventoryType.SlotType.CONTAINER, 23, ClickType.SHIFT_LEFT, InventoryAction.NOTHING);
//                        Bukkit.getServer().getPluginManager().callEvent(event);
//                    } else if (e.getClick().isLeftClick()) {
//                        takeItem(player, recipe);
//
//                        player.setItemOnCursor(new Item(recipe.getItem().getMaterial(), recipe.getItem().getAmount(), hotPotato, reforge, recombabulated, skin, enchants));
//                    }
//                }
//            }
//
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    if (player.getOpenInventory().getTitle().contains("Craft Item"))
//                        checkRecipe(player);
//                }
//            }.runTaskLater(SkyblockDragons.plugin, 1L);
//
//    private static void checkRecipe(Player player) {
//        boolean putBack = true;
//        Item[] items = new Item[9];
//        for (int i = 0; i < 9; i++) {
//            if (!Functions.isNotAir(player.getOpenInventory().getItem(Functions.numToSlot(i)))) {
//                items[i] = new Item(Items.NULL, 64);
//                continue;
//            }
//            try {
//                items[i] = new Item(Functions.getItemMaterial(player.getOpenInventory().getItem(Functions.numToSlot(i))), player.getOpenInventory().getItem(Functions.numToSlot(i)).getAmount());
//            } catch (NullPointerException ex) {
//                items[i] = new Item(Items.NULL, 64);
//            }
//        }
//        for (Recipe recipe : Recipe.recipes.values()) {
//            int amount = 0;
//            if (recipe.isShapeless()) {
//                Item[] itemsShapeLess = items.clone();
//                Item[] required = recipe.getItems().clone();
//                for (int i = 0; i < 9; i++) {
//                    if (itemsShapeLess[i].getMaterial() == Items.NULL) {
//                        amount++;
//                        continue;
//                    }
//                    for (int j = 0; j < 9; j++) {
//                        if (required[j] == null || required[j].getMaterial() == Items.NULL) {
//                            amount++;
//                            break;
//                        }
//                        if (itemsShapeLess[i].getMaterial().name().equals(required[j].getMaterial().name()) && itemsShapeLess[i].getAmount() == required[j].getAmount()) {
//                            itemsShapeLess[i] = new Item(Items.NULL, 64);
//                            required[j] = new Item(Items.NULL, 64);
//                            amount++;
//                            break;
//                        }
//                    }
//                }
//            } else {
//                for (int i = 0; i < 9; i++) {
//                    Item item1 = items[i];
//                    Item item2;
//
//                    try {
//                        item2 = recipe.getItems()[i];
//                    } catch (NullPointerException ex) {
//                        item2 = new Item(Items.NULL, 64);
//                    }
//
//                    try {
//                        if (item1.getMaterial().name().equals(item2.getMaterial().name()) && item1.getAmount() >= item2.getAmount())
//                            amount++;
//                    } catch (NullPointerException ex) {
//                        amount++;
//                    }
//                }
//            }
//            player.sendMessage(amount + "");
//            if (amount == 9) {
//                ItemStack itemToUpgrade = player.getOpenInventory().getItem(Functions.numToSlot(recipe.getSlotToUpgrade()));
//
//                int hotPotato;
//                ReforgeType reforge;
//                boolean recombabulated;
//                SkinMaterial skin;
//                Map<EnchantType, Short> enchants;
//
//                try {
//                    hotPotato = Functions.getHotPotato(itemToUpgrade);
//                    reforge = Functions.getReforge(itemToUpgrade);
//                    recombabulated = Functions.isRecombed(itemToUpgrade);
//                    skin = Functions.getSkin(itemToUpgrade);
//                    enchants = Functions.getEnchants(itemToUpgrade);
//                } catch (NullPointerException ex) {
//                    hotPotato = 0;
//                    reforge = ReforgeType.NULL;
//                    recombabulated = false;
//                    skin = SkinMaterial.NULL;
//                    enchants = new HashMap<>();
//                }
//
//                player.getOpenInventory().setItem(23, new Item(recipe.getItem().getMaterial(), recipe.getItem().getAmount(), hotPotato, reforge, recombabulated, skin, enchants));
//
//                putBack = false;
//                updateLine(player, false);
//            }
//        }
//        if (putBack) {
//            ItemStack item = Functions.createItem(Material.BARRIER, ChatColor.RED + "Recipe Required", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Add the items for valid recipe", ChatColor.GRAY + "in the crafting grid to the", ChatColor.GRAY + "left!")));
//            player.getOpenInventory().setItem(23, item);
//            updateLine(player, true);
//        }
//    }
//
//    private static void takeItem(Player player, Recipe recipe) {
//        for (int i = 0; i < 9; i++) {
//            try {
//                player.getOpenInventory().setItem(Functions.numToSlot(i), removeAmount(player.getOpenInventory().getItem(Functions.numToSlot(i)), recipe.getItems()[i].getAmount()));
//            } catch (NullPointerException ignored) {}
//        }
//    }
//
//    private static void updateLine(Player player, boolean red) {
//        for (int i = 45; i < 54; i++) {
//            if (i == 49) continue;
//            ItemStack glass = Functions.createItem(Material.STAINED_GLASS_PANE, 5,ChatColor.RESET + "");
//            if (red) {
//                glass = Functions.createItem(Material.STAINED_GLASS_PANE, 14,ChatColor.RESET + "");
//            }
//            player.getOpenInventory().setItem(i, glass);
//        }
//    }
//
//    private static ItemStack removeAmount(ItemStack itemStack, int amount) {
//        ItemStack item = itemStack.clone();
//        if (itemStack.getAmount() > amount) {
//            item.setAmount(itemStack.getAmount() - amount);
//        } else {
//            item = new ItemStack(Material.AIR);
//        }
//        return item;
//    }
//
//    private static boolean isEnoughItems(Player player) {
//        Item[] items = new Item[9];
//        for (int i = 0; i < 9; i++) {
//            if (!Functions.isNotAir(player.getOpenInventory().getItem(Functions.numToSlot(i)))) {
//                items[i] = new Item(Items.NULL, 64);
//                continue;
//            }
//            try {
//                items[i] = new Item(Functions.getItemMaterial(player.getOpenInventory().getItem(Functions.numToSlot(i))), player.getOpenInventory().getItem(Functions.numToSlot(i)).getAmount());
//            } catch (NullPointerException ex) {
//                items[i] = new Item(Items.NULL, 64);
//            }
//        }
//        for (Recipe recipe : Recipe.recipes.values()) {
//            int amount = 0;
//            if (recipe.isShapeless()) {
//                Item[] itemsShapeLess = items.clone();
//                Item[] required = recipe.getItems().clone();
//                for (int i = 0; i < 9; i++) {
//                    if (itemsShapeLess[i].getMaterial() == Items.NULL) continue;
//                    for (int j = 0; j < 9; j++) {
//                        if (required[j].getMaterial() == Items.NULL) continue;
//                        if (itemsShapeLess[i].getMaterial().name().equals(required[j].getMaterial().name()) && itemsShapeLess[i].getAmount() == required[j].getAmount()) {
//                            itemsShapeLess[i] = new Item(Items.NULL, 64);
//                            required[j] = new Item(Items.NULL, 64);
//                            amount++;
//                            break;
//                        }
//                    }
//                }
//            } else {
//                for (int i = 0; i < 9; i++) {
//                    Item item1 = items[i];
//                    Item item2;
//
//                    try {
//                        item2 = recipe.getItems()[i];
//                    } catch (NullPointerException ex) {
//                        item2 = new Item(Items.NULL, 64);
//                    }
//
//                    try {
//                        if (item1.getMaterial().name().equals(item2.getMaterial().name()) && item1.getAmount() >= item2.getAmount())
//                            amount++;
//                    } catch (NullPointerException ex) {
//                        amount++;
//                    }
//                }
//            }
//            player.sendMessage(amount + "");
//            if (amount == 9) {
//                return true;
//            }
//        }
//        return false;
//    }
}
