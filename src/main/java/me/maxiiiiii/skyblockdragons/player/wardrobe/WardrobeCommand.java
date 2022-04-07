package me.maxiiiiii.skyblockdragons.player.wardrobe;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.util.Functions.isPlayerName;
import static me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeMenu.openWardrobe;

public class WardrobeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
            if (args.length > 0) {
                if (isPlayerName(args[0])) {
                    player = SkyblockDragons.getPlayer(args[0]);
                } else {
                    sender.sendMessage(ChatColor.RED + "There is no player with name " + args[0]);
                }
            }
            openWardrobe(player, 1);
        }
        return true;
    }
}
