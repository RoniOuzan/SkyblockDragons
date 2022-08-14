package me.maxiiiiii.skyblockdragons.commands.manager;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface CommandConsoleRun {
    void perform(CommandSender sender, String[] args);
}
