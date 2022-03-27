package me.maxiiiiii.skyblockdragons.commands.menu;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemList {
    public static void openItemList(Player player, int page) {
        Inventory inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Item List " + page);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta glassItemMeta = glass.getItemMeta();
        glassItemMeta.setDisplayName(ChatColor.RESET + "");
        glass.setItemMeta(glassItemMeta);

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, glass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        Functions.setName(close, ChatColor.RED + "Close");
        Functions.setLore(close, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inv.setItem(48, close);

        ItemStack search = new ItemStack(Material.SIGN);
        Functions.setName(search, ChatColor.GREEN + "Search Items");
        Functions.setLore(search, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Search through all items.")));
        inv.setItem(49, search);

        ItemStack nextPage = new ItemStack(Material.ARROW);
        Functions.setName(nextPage, ChatColor.GREEN + "Next Page");
        Functions.setLore(nextPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go next page!")));
        inv.setItem(53, nextPage);

        ItemStack previousPage = new ItemStack(Material.ARROW);
        Functions.setName(previousPage, ChatColor.GREEN + "Previous Page");
        Functions.setLore(previousPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go previous page!")));
        inv.setItem(45, previousPage);

        String[] strings = new String[ItemMaterial.ItemMaterials.size()];

        int a = 0;
        for (String key : ItemMaterial.ItemMaterials.keySet()) {
            strings[a] = key;
            a++;
        }

        Arrays.sort(strings);

        ItemMaterial[] materials = new ItemMaterial[ItemMaterial.ItemMaterials.size()];
        a = 0;
        for (String string : strings) {
            materials[a] = ItemMaterial.ItemMaterials.get(string);
            a++;
        }

        int length = 28 * (page - 1);
        for (int i = 0; i < 28; i++) {
            try {
                inv.setItem(Functions.intToSlot(i), new Item(materials[i + length]));
            } catch (IndexOutOfBoundsException e){
                inv.setItem(Functions.intToSlot(i), new ItemStack(Material.AIR));
            }
        }

        player.openInventory(inv);
    }

    public static void openItemList(Player player, int num, String searchItem) {
        Inventory inv =  Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Item List " + num);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta glassItemMeta = glass.getItemMeta();
        glassItemMeta.setDisplayName(ChatColor.RESET + "");
        glass.setItemMeta(glassItemMeta);

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, glass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        Functions.setName(close, ChatColor.RED + "Close");
        Functions.setLore(close, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inv.setItem(48, close);

        ItemStack search = new ItemStack(Material.SIGN);
        Functions.setName(search, ChatColor.GREEN + "Search Items");
        Functions.setLore(search, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Search through all items.")));
        inv.setItem(49, search);

        ItemStack nextPage = new ItemStack(Material.ARROW);
        Functions.setName(nextPage, ChatColor.GREEN + "Next Page");
        Functions.setLore(nextPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go next page!")));
        inv.setItem(53, nextPage);

        ItemStack previousPage = new ItemStack(Material.ARROW);
        Functions.setName(previousPage, ChatColor.GREEN + "Previous Page");
        Functions.setLore(previousPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go previous page!")));
        inv.setItem(45, previousPage);

        ArrayList<ItemMaterial> materials = new ArrayList<>();
        for (ItemMaterial material : ItemMaterial.Items.values()) {
            if (material.getName().toLowerCase().contains(searchItem.toLowerCase())) {
                materials.add(material);
            }
        }

        int length = 28 * (num - 1);
        for (int i = 0; i < 28; i++) {
            try {
                inv.setItem(Functions.intToSlot(i), new Item(materials.get(i+ length)));
            } catch (IndexOutOfBoundsException e){
                inv.setItem(Functions.intToSlot(i), new ItemStack(Material.AIR));
            }
        }

        player.openInventory(inv);
    }
}
