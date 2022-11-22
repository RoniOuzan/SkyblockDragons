package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatsAdminCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        new PlayerStatsAdminMenu(player);
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", StatTypes.STATS.stream().map(StatType::name).collect(Collectors.toList())));
        return tabs;
    }
}
