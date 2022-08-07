package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.FlyTo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FlyToCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
            Entity target = player.getTargetEntity(100);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "You have to look at entity to execute this command!");
                return true;
            }
            long ticks = 20;
            if (args.length > 0) {
                ticks = Long.parseLong(args[0]);
            }
            new FlyTo(target, player, ticks, 1, true);
        }
        return true;
    }
}
