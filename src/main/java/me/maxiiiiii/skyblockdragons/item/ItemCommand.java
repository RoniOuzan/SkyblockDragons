package me.maxiiiiii.skyblockdragons.item;

import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.getItemMaterial;
import static me.maxiiiiii.skyblockdragons.util.Functions.isByte;
import static me.maxiiiiii.skyblockdragons.util.Functions.isItemMaterial;
import static me.maxiiiiii.skyblockdragons.util.Functions.isPlayerName;
import static me.maxiiiiii.skyblockdragons.util.Functions.openSign;
import static me.maxiiiiii.skyblockdragons.material.Items.items;
import static me.maxiiiiii.skyblockdragons.commands.menu.ItemList.openItemList;

public class ItemCommand implements CommandExecutor, Listener, TabCompleter {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getClickedInventory().getTitle().contains("Item List")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                String[] title = e.getClickedInventory().getTitle().split(" ");
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Search Items")) {
                    p.closeInventory();
                    // searchItem.put(p, true);
                    openSign(p, (lines) -> openItemList(p, 1, lines.get(0)));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Next Page")) {
                    p.closeInventory();
                    openItemList(p, Integer.parseInt(title[2]) + 1);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Previous Page")) {
                    if (title[2].equals("1")) {
                        p.sendMessage(ChatColor.RED + "You can't go to the previous page!");
                    } else {
                        openItemList(p, Integer.parseInt(title[2]) - 1);
                    }
                } else if (!e.getCurrentItem().getItemMeta().getDisplayName().contains("Close") && !e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RESET + "")) {
                    ItemMaterial material = getItemMaterial(e.getCurrentItem());
                    e.getWhoClicked().getInventory().addItem(new Item(material));
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item;
            byte amount = 1;
            if (args.length > 0 && !args[0].equalsIgnoreCase("list")) {
                if (isItemMaterial(args[0])) {
                    if (args.length > 1) {
                        if (isByte(args[1])) {
                            amount = Byte.parseByte(args[1]);
                            if (args.length > 2 && isPlayerName(args[2])) player = Bukkit.getPlayerExact(args[2]);
                        } else if (args.length > 2 && isByte(args[2])) {
                            amount = Byte.parseByte(args[2]);
                            if (isPlayerName(args[1])) player = Bukkit.getPlayerExact(args[1]);
                        } else if (isPlayerName(args[1])) {
                            player = Bukkit.getPlayerExact(args[1]);
                            if (args.length > 2 && isByte(args[2])) amount = Byte.parseByte(args[2]);
                        } else if (args.length > 2 && isPlayerName(args[2])) {
                            player = Bukkit.getPlayerExact(args[2]);
                            if (isByte(args[1])) amount = Byte.parseByte(args[1]);
                        }
                    }

                    for (int i = 0; i < amount; i++) {
                        item = new Item(items.get(args[0].toUpperCase()));
                        player.getInventory().addItem(item);
                    }
                    sender.sendMessage(ChatColor.GREEN + "Gave " + args[0].toUpperCase() + " to " + player.getName());
                } else {
                    for (ItemMaterial itemMaterial : items.values()) {
                        if (itemMaterial.name().contains(args[0].toUpperCase())) {
                            if (args.length > 1) {
                                if (isByte(args[1])) {
                                    amount = Byte.parseByte(args[1]);
                                    if (args.length > 2 && isPlayerName(args[2])) player = Bukkit.getPlayerExact(args[2]);
                                } else if (args.length > 2 && isByte(args[2])) {
                                    amount = Byte.parseByte(args[2]);
                                    if (isPlayerName(args[1])) player = Bukkit.getPlayerExact(args[1]);
                                } else if (isPlayerName(args[1])) {
                                    player = Bukkit.getPlayerExact(args[1]);
                                    if (args.length > 2 && isByte(args[2])) amount = Byte.parseByte(args[2]);
                                } else if (args.length > 2 && isPlayerName(args[2])) {
                                    player = Bukkit.getPlayerExact(args[2]);
                                    if (isByte(args[1])) amount = Byte.parseByte(args[1]);
                                }
                            }

                            for (int i = 0; i < amount; i++) {
                                player.getInventory().addItem(new Item(itemMaterial));
                            }
                            sender.sendMessage(ChatColor.GREEN + "Gave " + itemMaterial.name() + " to " + player.getName());
                        }
                    }
                }
            } else {
                openItemList(player, 1);
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
