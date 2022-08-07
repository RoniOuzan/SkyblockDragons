package me.maxiiiiii.skyblockdragons.worlds.hub;

import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).teleport(Warp.HUB.getLocation());
        }
        return true;
    }
}
