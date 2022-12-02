package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.List;

public class PlayerStatsAdminCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        PlayerSD target = args.length > 0 ? SkyblockDragons.getPlayer(args[0]) : player;
        new PlayerStatsAdminMenu(player, target);
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        return tabs;
    }
}
