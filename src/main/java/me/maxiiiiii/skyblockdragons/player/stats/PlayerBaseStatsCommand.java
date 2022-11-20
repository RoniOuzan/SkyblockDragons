package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerBaseStatsCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
            PlayerSD target = args.length > 2 && SkyblockDragons.getPlayers()
                    .stream().map(p -> p.getName().toLowerCase()).collect(Collectors.toList()).contains(args[2].toLowerCase()) ?
                    SkyblockDragons.getPlayer(args[2]) : player;

            StatType stat = StatTypes.get(args[0]);
            double amount;
            try {
                amount = Double.parseDouble(args[1]);
                target.getStats().getBaseStats().set(stat, amount);
                player.sendMessage(ChatColor.GREEN + "You have set the base " + stat.toString() + ChatColor.GREEN + " to " + amount + " for " + target.getName());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                target.getStats().getBaseStats().reset(stat);
                player.sendMessage(ChatColor.GREEN + "You have reset the base " + stat.toString() + " for " + target.getName());
            }
        }
//        new PlayerBaseStatsMenu(player); // doesn't work
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", StatTypes.STATS.stream().map(StatType::name).collect(Collectors.toList())));
        return tabs;
    }
}
