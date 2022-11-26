package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class PowerStoneAdminCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
             if (args[0].contains("add") || args[0].contains("learn")) {
                 if (args.length > 1) {
                     try {
                         PlayerSD target = args.length > 2 ? SkyblockDragons.getPlayer(args[2]) : player;
                         PowerStone stone = PowerStone.valueOf(args[1].toUpperCase());
                         player.getItems().getAccessoryBag().learnPowerStone(stone);
                         player.sendMessage(ChatColor.GREEN + "You have learned " + stone.getName() + " Power Stone to " + target.getName() + ".");
                     } catch (IllegalArgumentException ex) {
                         player.sendMessage(ChatColor.RED + "Can't understand the power stone " + args[1] + ".");
                     }
                 }
             } else if (args[0].contains("remove") || args[0].contains("unlearn")) {
                 if (args.length > 1) {
                     try {
                         PlayerSD target = args.length > 2 ? SkyblockDragons.getPlayer(args[2]) : player;
                         PowerStone stone = PowerStone.valueOf(args[1].toUpperCase());
                         player.getItems().getAccessoryBag().getLearnedPowerStones().remove(stone);
                         player.sendMessage(ChatColor.GREEN + "You have unlearned " + stone.getName() + " Power Stone to " + target.getName() + ".");
                     } catch (IllegalArgumentException ex) {
                         player.sendMessage(ChatColor.RED + "Can't understand the power stone " + args[1] + ".");
                     }
                 }
             } else if (args[0].contains("clear") || args[0].contains("reset")) {
                 PlayerSD target = args.length > 1 ? SkyblockDragons.getPlayer(args[2]) : player;
                 player.getItems().getAccessoryBag().getLearnedPowerStones().clear();
                 player.sendMessage(ChatColor.GREEN + "You have cleared all the learned Power Stones of " + target.getName() + ".");
             } else if (args[0].contains("select")) {
                 if (args.length > 1) {
                     try {
                         PlayerSD target = args.length > 2 ? SkyblockDragons.getPlayer(args[2]) : player;
                         PowerStone stone = PowerStone.valueOf(args[1].toUpperCase());
                         player.getItems().getAccessoryBag().setPowerStone(stone);
                         player.sendMessage(ChatColor.GREEN + "You have selected " + stone.getName() + " Power Stone to " + target.getName() + ".");
                     } catch (IllegalArgumentException ex) {
                         player.sendMessage(ChatColor.RED + "Can't understand the power stone " + args[1] + ".");
                     }
                 }
             }
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", "add", "learn", "remove", "unlearn", "clear", "reset"));
        List<String> arguments = PowerStone.getLearnPowerStones().stream().map(Enum::name).collect(Collectors.toList());
        tabs.add(new Argument(1, "add", arguments));
        tabs.add(new Argument(1, "learn", arguments));
        tabs.add(new Argument(1, "remove", arguments));
        tabs.add(new Argument(1, "unlearn", arguments));
        tabs.add(new Argument(1, "select", arguments));
        return tabs;
    }
}
