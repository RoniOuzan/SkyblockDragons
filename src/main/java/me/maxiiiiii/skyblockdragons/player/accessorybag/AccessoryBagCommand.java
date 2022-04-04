package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.storage.Variables;
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
            Variables.delete(e.getPlayer().getUniqueId(), "AccessoryBag", 0, 44);
            int i2 = 0;
            for (int i = 0; i < 45; i++) {
                if (Functions.isNotAir(e.getInventory().getItem(i))) {
                    Variables.set(e.getPlayer().getUniqueId(), "AccessoryBag", i2, e.getInventory().getItem(i));
//                    Item item = new Item(e.getInventory().getItem(i));
                    accessories.add(e.getInventory().getItem(i));
                    i2++;
                }
            }
            SkyblockDragons.players.get(e.getPlayer().getUniqueId()).setAccessoryBag(accessories);
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
