package me.maxiiiiii.skyblockdragons.player.party;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.List;

public class PartyListCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        player.performCommand("party list");
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        return tabs;
    }
}
