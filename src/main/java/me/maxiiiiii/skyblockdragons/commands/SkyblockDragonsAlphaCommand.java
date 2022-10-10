package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.commands.manager.QuickCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickSubCommand;
import me.maxiiiiii.skyblockdragons.events.JoinQuitListener;

public class SkyblockDragonsAlphaCommand extends QuickCommand {
    public SkyblockDragonsAlphaCommand() {
        addSubCommand(new QuickSubCommand("starter", (player, args) -> {
            player.sendMessage("§c[SBD ALPHA] You got Starter kit");
            JoinQuitListener.starterKit(player);
        }));
    }
}
