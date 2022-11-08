package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.List;

public class KillCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        PlayerSD p = args.length > 0 ? SkyblockDragons.getPlayer(args[0]) : player;
        p.kill();
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", SkyblockDragons.getOnlinePlayers()));
        return tabs;
    }
}
