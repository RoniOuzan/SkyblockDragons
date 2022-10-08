package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AccessoryBagCommand implements CommandExecutor, Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
       if (e.getInventory().getTitle().contains("Accessory Bag")) {
           String[] title = e.getInventory().getTitle().split(" ");
           if (title.length > 2) e.setCancelled(true);
           else if (e.getSlot() >= 45) {
               e.setCancelled(true);
           }
       }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().contains("Accessory Bag")) {
            String[] title = e.getInventory().getTitle().split(" ");
            if (title.length > 2) return;
            List<ItemStack> accessories = new ArrayList<>();
            for (int i = 0; i < 45; i++) {
                ItemStack item = e.getInventory().getItem(i);
                if (Functions.isNotAir(item) && Functions.getItemMaterial(item) instanceof AccessoryMaterial) {
                    accessories.add(item);
                }
            }
            SkyblockDragons.players.get(e.getPlayer().getUniqueId()).getAccessoryBag().setItems(accessories);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (Functions.isPlayerName(args[0])) {
                    player = Bukkit.getPlayerExact(args[0]);
                } else {
                    sender.sendMessage(ChatColor.RED + "There is no player with name " + args[0] + ".");
                    return true;
                }
            }
            AccessoryBagMenu.openAccessoryBag((Player) sender, player);
        }
        return true;
    }
}
