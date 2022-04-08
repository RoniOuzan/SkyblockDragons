package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.menu.SellMenu;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class SellCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
            SellMenu.openSellMenu(player);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (!e.getClickedInventory().getTitle().contains("Sell Menu")) return;

        if (e.getSlot() > 44) e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!e.getInventory().getTitle().contains("Sell Menu")) return;

        double amount = 0;
        for (int i = 0; i < 45; i++) {
            ItemMaterial material = Functions.getItemMaterial(e.getInventory().getItem(i));

            if (material == Items.NULL) continue;

            amount += material.getSellPrice();
        }

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getPlayer());
        player.addCoins(amount);
    }
}
