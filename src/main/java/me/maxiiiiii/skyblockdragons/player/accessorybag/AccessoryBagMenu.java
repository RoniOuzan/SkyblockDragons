package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
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

public class AccessoryBagMenu {
    public static void openAccessoryBag(Player player, Player arg) {
        Inventory inv;
        if (player.getName().equals(arg.getName())) {
            inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Accessory Bag");
        } else {
            inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Accessory Bag " + arg.getName());
        }
        ItemStack item = Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, item);
        }

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inv.setItem(49, close);

        PlayerSD playerSD = SkyblockDragons.getPlayer(player);
        List<ItemStack> accessories = playerSD.getAccessoryBag().getItems();
        for (int i = 0; i < 45; i++) {
            if (i < accessories.size())
                inv.setItem(i, accessories.get(i));
        }

        player.openInventory(inv);
    }
}
