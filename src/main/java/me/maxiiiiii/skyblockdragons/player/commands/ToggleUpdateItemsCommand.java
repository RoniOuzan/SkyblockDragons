package me.maxiiiiii.skyblockdragons.player.commands;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.List;

public class ToggleUpdateItemsCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        player.toggleUpdateItems();
        if (player.isUpdateItems())
            player.sendMessage(ChatColor.GREEN + "Now your items will be updated");
        else
            player.sendMessage(ChatColor.RED + "Now your items will not be updated");
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        return tabs;
    }
}
