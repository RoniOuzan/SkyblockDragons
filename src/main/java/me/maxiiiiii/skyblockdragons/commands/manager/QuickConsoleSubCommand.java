package me.maxiiiiii.skyblockdragons.commands.manager;

import org.bukkit.command.CommandSender;

public class QuickConsoleSubCommand extends SubConsoleCommand {
    public String name = "";
    public String description = "";
    public String syntax = "";
    public CommandConsoleRun run = null;

    public QuickConsoleSubCommand(String name, String description, String syntax, CommandConsoleRun run) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.run = run;
    }

    public QuickConsoleSubCommand(String name, CommandConsoleRun run) {
        this.name = name;
        this.run = run;
        this.syntax = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        run.perform(sender, args);
    }
}
