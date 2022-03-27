package me.maxiiiiii.skyblockdragons.itemcreator.anvil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class AnvilGui {
    public static void updateUpgrade(Inventory inv, boolean red) {
        ItemStack upgrade;
        if (red) {
            upgrade = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        }
        else {
            upgrade = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        }
        setName(upgrade, ChatColor.GOLD + "Item To Upgrade");
        setLore(upgrade, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "The item you want to upgrade", ChatColor.GRAY + "should be placed in the slot on", ChatColor.GRAY + "this side.")));
        inv.setItem(11, upgrade);
        inv.setItem(12, upgrade);
        inv.setItem(20, upgrade);
    }

    public static void updateSacrifice(Inventory inv, boolean red, boolean advanced) {
        ItemStack sacrifice;
        if (red) {
            sacrifice = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        } else {
            sacrifice = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        }
        if (advanced) {
            setName(sacrifice, ChatColor.GOLD + "Reforge Stone");
        } else {
            setName(sacrifice, ChatColor.GOLD + "Item To Sacrifice");
        }
        setLore(sacrifice, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "The item you want to sacrifice in", ChatColor.GRAY + "order to upgrade the item on the", ChatColor.GRAY + "left should be placed in the", ChatColor.GRAY + "slot on this side.")));
        inv.setItem(14, sacrifice);
        inv.setItem(15, sacrifice);
        inv.setItem(24, sacrifice);
    }

    public static void updateLine(Inventory inv, boolean red) {
        ItemStack glass;
        if (red) {
            glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        } else {
            glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        }
        setName(glass, ChatColor.RESET + "");
        for (int i = 45; i < 54; i++) {
            if (i == 49) {
                continue;
            }
            inv.setItem(i, glass);
        }
    }

    public static void openAnvil(Player player, boolean advanced) {
        Inventory inv;
        if (advanced) {
            inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Anvil (Advanced)" );
        } else {
            inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Anvil");
        }

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        setName(glass, ChatColor.RESET + "");
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, glass);
        }

        ItemStack redGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        setName(redGlass, ChatColor.RESET + "");
        for (int i = 45; i < 54; i++) {
            if (i == 49) {
                continue;
            }
            inv.setItem(i, redGlass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        setName(close, ChatColor.RED + "Close");
        inv.setItem(49, close);

        updateUpgrade(inv, true);

        updateSacrifice(inv, true, advanced);

        ItemStack combine = new ItemStack(Material.ANVIL);
        setName(combine, ChatColor.GREEN + "Combine Items");
        setLore(combine, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Combine the items in the slots", ChatColor.GRAY + "to the left and right below")));
        inv.setItem(22, combine);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        setName(barrier, ChatColor.RED + "Anvil");
        setLore(barrier, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Place a target item in the left", ChatColor.GRAY + "slot and a sacrifice item in the", ChatColor.GRAY + "right slot to combine", ChatColor.GRAY + "Enchantments and Others!")));
        inv.setItem(13, barrier);

        inv.setItem(29, new ItemStack(Material.AIR));
        inv.setItem(33, new ItemStack(Material.AIR));

        player.openInventory(inv);
    }
}
