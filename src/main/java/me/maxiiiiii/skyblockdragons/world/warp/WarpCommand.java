package me.maxiiiiii.skyblockdragons.world.warp;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Invalid arguments");
            }

            Warp warp = null;
            for (Warp value : Warp.values()) {
                if ((value.name().startsWith(args[0].toUpperCase())) || (value.getAlias() != null && value.getAlias().startsWith(args[0].toUpperCase())))
                    warp = value;
            }

            if (warp == null) {
                player.sendMessage(ChatColor.RED + "This is no warp with this name!");
                return true;
            }

            PlayerWarpEvent event = new PlayerWarpEvent(player, warp);
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
        return true;
    }
}
