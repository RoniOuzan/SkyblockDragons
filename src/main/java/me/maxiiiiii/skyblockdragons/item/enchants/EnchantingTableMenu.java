package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.EnchantModifier;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantingTableMenu {
    public static void openEnchantingTable(Player player, ItemStack item) {
        Inventory inventory = Bukkit.createInventory(player, 54, "Enchanting Table");

        Functions.putGlasses(inventory);

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(49, close);

        inventory.setItem(19, item);
        inventory.setItem(28, Functions.createItem(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Enchant Item", ChatColor.GRAY + "Add an item to the slot above to", ChatColor.GRAY + "view enchantment options!"));

        if (item != null) {
            ItemMaterial material = Items.get(inventory.getItem(19));

            int i = 0;
            for (EnchantType enchantType : EnchantType.enchants.values()) {
                if (!enchantType.getTypes().contains(material.getType()) || !enchantType.isInEnchantingTable())
                    continue;
                if (i >= 15) break;

                List<String> lores = Functions.loreBuilder(enchantType.getDescription(1));
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to view!");
                ItemStack enchantItem = Functions.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + enchantType.toString(), lores);
                inventory.setItem(EnchantingTableMenu.intToSlot(i), enchantItem);
                i++;
            }
        }

        player.openInventory(inventory);
    }

    public static void openEnchantMenu(Player player, EnchantType enchantType, Item item) {
        Inventory inventory = Bukkit.createInventory(player, 54, "Enchant Item");

        Functions.putGlasses(inventory);

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(49, close);

        ItemStack goBack = Functions.createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

        inventory.setItem(13, item);

        if (enchantType.getMaxLevel() == 1) {
            Map<EnchantType, Short> enchants = new HashMap<>();
            enchants.put(enchantType, (short) 1);
            inventory.setItem(31, new Item(SkyblockDragons.getPlayer(player), Items.get("ENCHANTED_BOOK"), new EnchantModifier(enchants)));
            List<String> lores = inventory.getItem(31).getItemMeta().getLore();
            lores.add("");
            lores.add(ChatColor.GRAY + "Costs: " + ChatColor.DARK_AQUA + 5 + " Exp");
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click to apply!");
            Functions.setLore(inventory.getItem(31), lores);
        }

        if (enchantType.getMaxLevel() == 2) {
            Map<EnchantType, Short> enchants = new HashMap<>();
            for (short i = 1; i < 3; i++) {
                enchants.put(enchantType, i);
                inventory.setItem(29 + i + (i == 2 ? 1 : 0), new Item(SkyblockDragons.getPlayer(player), Items.get("ENCHANTED_BOOK"), new EnchantModifier(enchants)));
                List<String> lores = inventory.getItem(29 + i + (i == 2 ? 1 : 0)).getItemMeta().getLore();
                lores.add("");
                lores.add(ChatColor.GRAY + "Costs: " + ChatColor.DARK_AQUA + i + " Exp");
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to apply!");
                Functions.setLore(inventory.getItem(29 + i + (i == 2 ? 1 : 0)), lores);
            }
        }

        if (enchantType.getMaxLevel() == 3) {
            Map<EnchantType, Short> enchants = new HashMap<>();
            for (short i = 1; i < 4; i++) {
                enchants.put(enchantType, i);
                inventory.setItem(29 + i, new Item(SkyblockDragons.getPlayer(player), Items.get("ENCHANTED_BOOK"), new EnchantModifier(enchants)));
                List<String> lores = inventory.getItem(29 + i).getItemMeta().getLore();
                lores.add("");
                lores.add(ChatColor.GRAY + "Costs: " + ChatColor.DARK_AQUA + i + " Exp");
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to apply!");
                Functions.setLore(inventory.getItem(29 + i), lores);
            }
        }

        if (enchantType.getMaxLevel() == 4) {
            Map<EnchantType, Short> enchants = new HashMap<>();
            for (short i = 1; i < 5; i++) {
                enchants.put(enchantType, i);
                inventory.setItem(28 + i + (i > 2 ? 1 : 0), new Item(SkyblockDragons.getPlayer(player), Items.get("ENCHANTED_BOOK"), new EnchantModifier(enchants)));
                List<String> lores = inventory.getItem(28 + i + (i > 2 ? 1 : 0)).getItemMeta().getLore();
                lores.add("");
                lores.add(ChatColor.GRAY + "Costs: " + ChatColor.DARK_AQUA + i + " Exp");
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to apply!");
                Functions.setLore(inventory.getItem(28 + i + (i > 2 ? 1 : 0)), lores);
            }
        }

        if (enchantType.getMaxLevel() == 5) {
            Map<EnchantType, Short> enchants = new HashMap<>();
            for (short i = 1; i < 6; i++) {
                enchants.put(enchantType, i);
                inventory.setItem(28 + i, new Item(SkyblockDragons.getPlayer(player), Items.get("ENCHANTED_BOOK"), new EnchantModifier(enchants)));
                List<String> lores = inventory.getItem(28 + i).getItemMeta().getLore();
                lores.add("");
                lores.add(ChatColor.GRAY + "Costs: " + ChatColor.DARK_AQUA + i + " Exp");
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to apply!");
                Functions.setLore(inventory.getItem(28 + i), lores);
            }
        }

        player.openInventory(inventory);
    }

    public static int intToSlot(int num) {
        return (num % 5) + 12 + ((num / 5) * 9);
    }
}
