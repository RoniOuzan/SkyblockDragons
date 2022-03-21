package me.maxiiiiii.skyblockdragons.wardrobe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.Functions.*;
import static me.maxiiiiii.skyblockdragons.Functions.isPlayerName;
import static me.maxiiiiii.skyblockdragons.wardrobe.WardrobeMenu.openWardrobe;

public class WardrobeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (isPlayerName(args[0])) {
                    player = Bukkit.getPlayer(args[0]);
                } else {
                    sender.sendMessage(ChatColor.RED + "There is no player with name " + args[0]);
                }
            }
            openWardrobe(player, 1);
        }
        return true;
    }
}
