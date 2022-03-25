package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (Functions.isPlayerName(args[0])) {
                    player = Bukkit.getPlayer(args[0]);
                } else if (args[0].equalsIgnoreCase("disable")) {
                    SkyblockDragons.disablePlayTime = true;
                    sender.sendMessage(ChatColor.RED + "You have been disabled playtime until the next reload!");
                    return true;
                } else if (args[0].equalsIgnoreCase("enable")) {
                    SkyblockDragons.disablePlayTime = false;
                    sender.sendMessage(ChatColor.RED + "You have been enabled playtime until the next reload!");
                    return true;
                } else if (args[0].equalsIgnoreCase("toggle")) {
                    SkyblockDragons.disablePlayTime = !SkyblockDragons.disablePlayTime;
                    if (SkyblockDragons.disablePlayTime) {
                        sender.sendMessage(ChatColor.RED + "You have been enabled playtime until the next reload!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "You have been disabled playtime until the next reload!");
                    }
                    return true;
                }
            }

            long timePlayed = SkyblockDragons.playTime.get(player.getUniqueId()) / 1200;

            long hours = timePlayed / 60;
            long minutes = timePlayed - (hours * 60);

            player.sendMessage(ChatColor.GREEN + "You have " + hours + " hours and " + minutes + " minutes playtime!");
        }
        return true;
    }
}
