package me.maxiiiiii.skyblockdragons.player.storage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class StorageMenu extends Menu {
    public StorageMenu(PlayerSD player) {
        super(player, "Storage", 6, InventoryGlassType.ALL, false);
    }

    @Override
    public void update() {
        this.setItem(4, createItem(Material.ENDER_CHEST, ChatColor.DARK_PURPLE + "Ender Chest", "", ChatColor.GRAY + "Store your items in your ender chest", ChatColor.GRAY + "to use them everytime you want."));

        for (int i = 0; i < 9; i++) {
            this.setItem(i + 9, createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Ender Chest Page " + (i + 1), "PAGE_" + i, ChatColor.GRAY + "Store your items in your ender chest", ChatColor.GRAY + "to use them everytime you want.", "", ChatColor.YELLOW + "Click to open!"));
        }

        this.setItem(22, createItem(Material.CHEST, ChatColor.GOLD + "Storage", "", ChatColor.GRAY + "Store your backpacks with your items in them."));

        for (int i = 0; i < 18; i++) {
            this.setItem(i + 27, createItem(Material.STAINED_GLASS_PANE, 1, ChatColor.GOLD + "Backpack Slot " + i, "BACKPACK_SLOT_" + i, ChatColor.YELLOW + "Click here with backpack to place it."));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).contains("PAGE_")) {
            int page = Integer.parseInt(this.getNBT(e.getCurrentItem()).split("_")[1]);
            new EnderChestMenu(this.player, page);
        }
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent e) {

    }

    public static class Command implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
            if (sender instanceof Player) {
                PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
                new StorageMenu(player);
            }
            return true;
        }
    }
}
