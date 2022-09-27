package me.maxiiiiii.skyblockdragons.world.warp;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WarpCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Invalid arguments");
            return;
        }

        Warp warp = null;
        for (Warp value : Warp.values()) {
            if ((value.name().startsWith(args[0].toUpperCase())) || (value.getAlias() != null && value.getAlias().startsWith(args[0].toUpperCase())))
                warp = value;
        }

        if (warp == null) {
            player.sendMessage(ChatColor.RED + "This is no warp with this name!");
            return;
        }

        warp.warp(player);
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        if (player.hasPermission("skyblockdragons.warp")) {
            tabs.add(new Argument(0, "", Arrays.stream(Warp.values()).map(Enum::name).collect(Collectors.toList())));
        }
        return tabs;
    }
}
