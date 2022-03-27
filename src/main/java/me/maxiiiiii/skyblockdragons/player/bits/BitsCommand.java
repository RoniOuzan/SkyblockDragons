package me.maxiiiiii.skyblockdragons.player.bits;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BitsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give")) {
                    if (args.length > 1) {
                        if (args.length > 2) {
                            player = Bukkit.getPlayerExact(args[2]);
                            if (player == null) {
                                sender.sendMessage(ChatColor.RED + "There is no player with name " + ChatColor.GREEN + args[2] + ChatColor.RED + ".");
                                return true;
                            }
                        }
                        BitsUtil.add(player, Long.parseLong(args[1]));
                        sender.sendMessage(ChatColor.GREEN + "You gave " + Functions.getNumberFormat(Double.parseDouble(args[1])) + " to " + player.getName() + ".");
                    }
                } else if (args[0].equalsIgnoreCase("send") || args[0].equalsIgnoreCase("info")) {
                    if (args.length > 1) {
                        player = Bukkit.getPlayerExact(args[1]);
                        if (player == null) {
                            sender.sendMessage(ChatColor.RED + "There is no player with name " + ChatColor.GREEN + args[2] + ChatColor.RED + ".");
                            return true;
                        }
                    }
                    sender.sendMessage(ChatColor.GREEN + player.getName() + " has " + ChatColor.AQUA + SkyblockDragons.bits.get(player.getUniqueId()) + ChatColor.GREEN + ".");
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (args.length > 1) {
                        if (args.length > 2) {
                            player = Bukkit.getPlayerExact(args[1]);
                            if (player == null) {
                                sender.sendMessage(ChatColor.RED + "There is no player with name " + ChatColor.GREEN + args[2] + ChatColor.RED + ".");
                                return true;
                            }
                        }
                        BitsUtil.set(player, Long.parseLong(args[1]));
                        sender.sendMessage(ChatColor.GREEN + "You have been changed " + player.getName() + "'s bits to " + args[1]);
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid arguments!");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final String[] tabList = {"add", "give", "info", "send", "set"};
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length > 1) {
            for (String tab : tabList) {
                if (tab.startsWith(args[1])) {
                    tabs.add(tab);
                }
            }

            Collections.sort(tabs);

            return tabs;
        }
        return tabs;
    }
}
