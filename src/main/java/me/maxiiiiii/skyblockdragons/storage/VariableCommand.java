package me.maxiiiiii.skyblockdragons.storage;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class VariableCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reset")) {
                if (args.length > 1) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null)
                        target = player;
                    Variables.reset(target.getUniqueId());
                    player.sendMessage(ChatColor.GREEN + "You have reset the variables for " + player + ".");
                } else {
                    Variables.resetAll();
                    player.sendMessage(ChatColor.GREEN + "You have reset all variables.");
                }

                player.performCommand("sd reload");
            }
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", "reset"));
        return tabs;
    }
}
