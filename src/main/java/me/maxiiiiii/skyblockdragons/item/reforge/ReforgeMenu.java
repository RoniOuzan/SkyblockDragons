package me.maxiiiiii.skyblockdragons.item.reforge;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class ReforgeMenu {
    public static final HashMap<Player, Rarity> raritySelected = new HashMap<>();

    public static final ItemStack anvil = Functions.createItem(Material.ANVIL, ChatColor.GREEN + "Reforge Item", ChatColor.GRAY + "Place an item above to reforge", ChatColor.GRAY + "it! Reforging items adds a", ChatColor.GRAY + "random modifier to the item that", ChatColor.GRAY + "grants stats boosts.", "", ChatColor.YELLOW + "Click to reforge!");

    public static void openReforgeMenu(Player player, boolean isAccessoryBag) {
        Inventory inv = Bukkit.createInventory(player, 45, ChatColor.DARK_GRAY + "Reforge Menu");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        for (int i = 0; i < 45; i++) {
            inv.setItem(i, glass);
        }

        ItemStack redGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        int n = 0;
        for (int i = 0; i < 5; i++) {
            inv.setItem(n, redGlass);
            n += 9;
        }
        n = 8;
        for (int i = 0; i < 5; i++) {
            inv.setItem(n, redGlass);
            n += 9;
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        setName(close, ChatColor.RED + "Close");
        setLore(close, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inv.setItem(40, close);

        if (isAccessoryBag) {
            ItemStack accessoryBag = Functions.createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Reforge Accessory Bag");
            Functions.applySkull(accessoryBag, "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0=");
            inv.setItem(13, accessoryBag);
            ItemStack anvil = ReforgeMenu.anvil.clone();
            List<String> lores = anvil.getItemMeta().getLore();
            lores.remove(lores.size() - 1);
            int cost = ReforgeCommand.getCost(inv, SkyblockDragons.getPlayer(player));
            lores.add(ChatColor.GRAY + "Cost: " + ChatColor.GOLD + Functions.getNumberFormat(cost));
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click to reforge!");
            Functions.setLore(anvil, lores);
            inv.setItem(22, anvil);
        } else {
            inv.setItem(13, new ItemStack(Material.AIR));
            inv.setItem(22, anvil);
        }


        if (!isAccessoryBag) {
            ItemStack accessoryBag = Functions.createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Reforge Accessory Bag");
            Functions.applySkull(accessoryBag, "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0=");
            inv.setItem(41, accessoryBag);
        }

        if (isAccessoryBag) {
            ItemStack rarity = Functions.createItem(Material.EYE_OF_ENDER, ChatColor.GREEN + "Rarity");
            Rarity selected = raritySelected.getOrDefault(player, Rarity.COMMON);
            ArrayList<String> rarityLores = new ArrayList<>();
            for (Rarity value : Rarity.values()) {
                if (value == selected) {
                    rarityLores.add(value.getColor() + Functions.setTitleCase(value.name().replace("_", " ")) + " " + ChatColor.WHITE + "<--");
                } else {
                    rarityLores.add(value.getColor() + Functions.setTitleCase(value.name().replace("_", " ")));
                }
            }
            rarityLores.add("");
            rarityLores.add(ChatColor.YELLOW + "Left-click to switch rarity!");
            rarityLores.add(ChatColor.YELLOW + "Right-click to switch rarity backwards!");
            Functions.setLore(rarity, rarityLores);
            inv.setItem(42, rarity);
        }

        player.openInventory(inv);
    }
}
