package me.maxiiiiii.skyblockdragons.commands.manager;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(PlayerSD player, String[] args);

}
