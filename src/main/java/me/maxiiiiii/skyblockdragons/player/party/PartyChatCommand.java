package me.maxiiiiii.skyblockdragons.player.party;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.List;

public class PartyChatCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (player.getParty() == null) {
            player.sendMessage(ChatColor.RED + "You don't have a party!");
            return;
        }

        if (args.length == 0) {
            player.sendMessage("/partychat <message>");
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }
        player.getParty().makePlayerSay(player, message.toString());
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        return tabs;
    }
}
