package me.maxiiiiii.skyblockdragons.pet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.Functions.*;

public class PetMenu {
    public void openPetMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "Pet Menu");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta glassItemMeta = glass.getItemMeta();
        glassItemMeta.setDisplayName(ChatColor.RESET + "");
        glass.setItemMeta(glassItemMeta);

        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        setName(close, ChatColor.RED + "Close");
        setLore(close, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inventory.setItem(49, close);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);



        player.openInventory(inventory);
    }
}
