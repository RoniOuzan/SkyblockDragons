package me.maxiiiiii.skyblockdragons.commands.manager;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.entity.Player;

public class QuickSubCommand extends SubCommand {
    public String name = "";
    public String description = "";
    public String syntax = "";
    public CommandRun run = null;

    public QuickSubCommand(String name, String description, String syntax, CommandRun run) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.run = run;
    }

    public QuickSubCommand(String name, CommandRun run) {
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
    public void perform(Player player, String[] args) {
        run.perform(SkyblockDragons.getPlayer(player), args);
    }
}
