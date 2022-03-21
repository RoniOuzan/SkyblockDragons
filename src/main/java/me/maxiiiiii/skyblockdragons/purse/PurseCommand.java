package me.maxiiiiii.skyblockdragons.purse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.Functions.getNumberFormat;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.purses;

public class PurseCommand implements CommandExecutor {
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
                        PurseUtil.add(player, Double.parseDouble(args[1]));
                        sender.sendMessage(ChatColor.GREEN + "You gave " + getNumberFormat(Double.parseDouble(args[1])) + " to " + player.getName() + ".");
                    }
                } else if (args[0].equalsIgnoreCase("send") || args[0].equalsIgnoreCase("info")) {
                    if (args.length > 1) {
                        player = Bukkit.getPlayerExact(args[1]);
                        if (player == null) {
                            sender.sendMessage(ChatColor.RED + "There is no player with name " + ChatColor.GREEN + args[2] + ChatColor.RED + ".");
                            return true;
                        }
                    }
                    sender.sendMessage(ChatColor.GREEN + player.getName() + " has " + ChatColor.GOLD + purses.get(player.getUniqueId()) + ChatColor.GREEN + ".");
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (args.length > 1) {
                        if (args.length > 2) {
                            player = Bukkit.getPlayerExact(args[1]);
                            if (player == null) {
                                sender.sendMessage(ChatColor.RED + "There is no player with name " + ChatColor.GREEN + args[2] + ChatColor.RED + ".");
                                return true;
                            }
                        }
                        PurseUtil.set(player, Double.parseDouble(args[1]));
                        sender.sendMessage(ChatColor.GREEN + "You have been changed " + player.getName() + "'s purse to " + args[1]);
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid arguments!");
            }
        }
        return true;
    }
}
