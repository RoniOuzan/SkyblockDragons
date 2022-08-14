package me.maxiiiiii.skyblockdragons.commands.manager;

import org.bukkit.command.CommandSender;

public abstract class SubConsoleCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(CommandSender sender, String[] args);

}
