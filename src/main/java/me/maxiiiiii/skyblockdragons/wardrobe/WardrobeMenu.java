package me.maxiiiiii.skyblockdragons.wardrobe;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class WardrobeMenu {
    public static void openWardrobe(Player player, int page) {
        Inventory inventory = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Wardrobe (" + ((page > 1) ? 2 : 1) + "/2)");

        PlayerSD PlayerSD = players.get(player.getUniqueId());

        int startValue = (page > 1) ? 9 : 0;
        for (int i = startValue; i < 9 + startValue; i++) {
            for (int j = 0; j < 4; j++) {
                inventory.setItem(numberToItemSlot(i, j), PlayerSD.getWardrobe().getSlot(i).getPeace(j));
            }
        }

        int adder = (page > 1) ? 9 : 0;
        for (int i = 36; i < 45; i++) {
            if (PlayerSD.getWardrobe().getSlot(i - 36 + adder).getHelmet().getType() == Material.STAINED_GLASS_PANE && PlayerSD.getWardrobe().getSlot(i - 36 + adder).getChestplate().getType() == Material.STAINED_GLASS_PANE && PlayerSD.getWardrobe().getSlot(i - 36 + adder).getLeggings().getType() == Material.STAINED_GLASS_PANE && PlayerSD.getWardrobe().getSlot(i - 36 + adder).getBoots().getType() == Material.STAINED_GLASS_PANE) {
                inventory.setItem(i, createItem(Material.INK_SACK, 1, 8, ChatColor.GRAY + "Slot " + ((i - 35) + ((page > 1) ? 9 : 0)) + ": " + ChatColor.RED + "Empty", ChatColor.GRAY + "This wardrobe slot contains no", ChatColor.GRAY + "armor."));
            } else {
                inventory.setItem(i, createItem(Material.INK_SACK, 1, 9, ChatColor.GRAY + "Slot " + ((i - 35) + ((page > 1) ? 9 : 0)) + ": " + ChatColor.GREEN + "Ready", ChatColor.GRAY + "This wardrobe slot is ready to", ChatColor.GRAY + "be equipped.", "", ChatColor.YELLOW + "Click to equip this armor set!"));
            }
        }

        int currentSlot = Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0"));
        if (currentSlot != 0) {
            if (page > 1 && currentSlot > 9) {
                inventory.setItem(currentSlot + 26, createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + (currentSlot) + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            } else if (page < 2 && currentSlot <= 9) {
                inventory.setItem(currentSlot + 35, createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + (currentSlot) + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            }
        }

        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, createItem(Material.STAINED_GLASS_PANE, 1, 15, ChatColor.RESET + ""));
        }

        ItemStack close = createItem(Material.BARRIER, 1, ChatColor.RED + "Close");
        inventory.setItem(49, close);

        ItemStack goBack = createItem(Material.ARROW, 1, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "To Skyblock Menu");
        inventory.setItem(48, goBack);

        if (page > 1) {
            ItemStack previousPage = createItem(Material.ARROW, 1, ChatColor.GREEN + "Previous Page", ChatColor.YELLOW + "Page 1");
            inventory.setItem(45, previousPage);
        } else {
            ItemStack nextPage = createItem(Material.ARROW, 1, ChatColor.GREEN + "Next Page", ChatColor.YELLOW + "Page 2");
            inventory.setItem(53, nextPage);
        }

        player.openInventory(inventory);
    }
}
