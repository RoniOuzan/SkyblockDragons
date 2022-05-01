package me.maxiiiiii.skyblockdragons.item;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.menus.ItemListMenu;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.item.material.Items.items;
import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class ItemCommand implements CommandExecutor, Listener, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
            ItemStack item;
            byte amount = 1;
            if (args.length > 0 && !args[0].equalsIgnoreCase("list")) {
                if (isItemMaterial(args[0])) {
                    if (args.length > 1) {
                        if (isByte(args[1])) {
                            amount = Byte.parseByte(args[1]);
                            if (args.length > 2 && isPlayerName(args[2])) player = SkyblockDragons.getPlayer(args[2]);
                        } else if (args.length > 2 && isByte(args[2])) {
                            amount = Byte.parseByte(args[2]);
                            if (isPlayerName(args[1])) player = SkyblockDragons.getPlayer(args[1]);
                        } else if (isPlayerName(args[1])) {
                            player = SkyblockDragons.getPlayer(args[1]);
                            if (args.length > 2 && isByte(args[2])) amount = Byte.parseByte(args[2]);
                        } else if (args.length > 2 && isPlayerName(args[2])) {
                            player = SkyblockDragons.getPlayer(args[2]);
                            if (isByte(args[1])) amount = Byte.parseByte(args[1]);
                        }
                    }

                    for (int i = 0; i < amount; i++) {
                        item = new Item(player, items.get(args[0].toUpperCase()));
                        player.getInventory().addItem(item);
                    }
                    sender.sendMessage(ChatColor.GREEN + "Gave " + args[0].toUpperCase() + " to " + player.getName());
                } else {
                    for (ItemMaterial itemMaterial : items.values()) {
                        if (itemMaterial.name().contains(args[0].toUpperCase())) {
                            if (args.length > 1) {
                                if (isByte(args[1])) {
                                    amount = Byte.parseByte(args[1]);
                                    if (args.length > 2 && isPlayerName(args[2])) player = SkyblockDragons.getPlayer(args[2]);
                                } else if (args.length > 2 && isByte(args[2])) {
                                    amount = Byte.parseByte(args[2]);
                                    if (isPlayerName(args[1])) player = SkyblockDragons.getPlayer(args[1]);
                                } else if (isPlayerName(args[1])) {
                                    player = SkyblockDragons.getPlayer(args[1]);
                                    if (args.length > 2 && isByte(args[2])) amount = Byte.parseByte(args[2]);
                                } else if (args.length > 2 && isPlayerName(args[2])) {
                                    player = SkyblockDragons.getPlayer(args[2]);
                                    if (isByte(args[1])) amount = Byte.parseByte(args[1]);
                                }
                            }

                            for (int i = 0; i < amount; i++) {
                                player.getInventory().addItem(new Item(player, itemMaterial));
                            }
                            sender.sendMessage(ChatColor.GREEN + "Gave " + itemMaterial.name() + " to " + player.getName());
                        }
                    }
                }
            } else {
                new ItemListMenu(player, "").open();
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length == 1) {
            for (ItemMaterial tab : items.values()) {
                if (tab.name().startsWith(args[0].toUpperCase())) {
                    tabs.add(tab.name());
                }
            }
            Collections.sort(tabs);
            return tabs;
        }
        return null;
    }
}
