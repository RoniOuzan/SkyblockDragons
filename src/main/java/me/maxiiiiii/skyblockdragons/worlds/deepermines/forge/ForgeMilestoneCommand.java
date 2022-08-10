package me.maxiiiiii.skyblockdragons.worlds.deepermines.forge;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.List;

public class ForgeMilestoneCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
            if (args[0].startsWith("a")) {
                player.getForge().getMilestone().addAmount();
            } else if (args[0].startsWith("s")) {
                if (args.length > 1)
                    player.getForge().getMilestone().setAmount(Integer.parseInt(args[1]));
                else {
                    player.sendMessage("/ForgeMilestone " + args[0] + " <amount>");
                }
            } else if (args[0].startsWith("r")) {
                player.getForge().getMilestone().setAmount(0);
            }
        }
    }

    @Override
    public List<Argument> tabComplete(List<Argument> tabs) {
        return tabs;
    }
}
