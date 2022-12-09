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
            new FastTravelMenu(player);
            return;
        }

        Warp warp = null;
        String warpName = args[0].toUpperCase();
        for (Warp value : Warp.values()) {
            if ((value.name().startsWith(warpName)) || (value.getAlias() != null && value.getAlias().startsWith(warpName))) {
                warp = value;
                break;
            }
        }

        if (warp == null) {
            player.sendMessage(ChatColor.RED + "This is no warp with this name!");
            return;
        }

        if (!warp.getRequirements().hasRequirements(player) || !warp.getWorld().getRequirements().hasRequirements(player)) {
            player.sendMessage(ChatColor.RED + "You don't have the requirements to warp!");
            return;
        } else if (!player.getVisitedWorlds().contains(warp.getWorld())) {
            player.sendMessage(ChatColor.RED + "You have not been in this world yet! visit it in a different way");
            return;
        }

        warp.warp(player);
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        if (player.hasPermission("skyblockdragons.warp")) {
            tabs.add(new Argument(0, "", Arrays.stream(Warp.values()).map(Enum::name).collect(Collectors.toList())));
        }

//        SkyblockDragons.logger.info(String.format("Warp complete for %s: %s", player.getName(), tabs));
        return tabs;
    }
}
