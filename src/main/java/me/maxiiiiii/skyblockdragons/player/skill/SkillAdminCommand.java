package me.maxiiiiii.skyblockdragons.player.skill;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class SkillAdminCommand extends CommandSD {

    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 2) {
            if (Functions.isSkillName(args[0])) {
                if (args.length > 3 && Functions.isPlayerName(args[3])) {
                    player = SkyblockDragons.getPlayer(args[3]);
                }
                if (args[1].equalsIgnoreCase("give")) {
                    try {
                        double amount = Double.parseDouble(args[2]);
                        player.getSkills().get(args[0]).giveXp(amount);
                        player.sendMessage(ChatColor.GREEN + "Gave " + args[2] + " " + Functions.setTitleCase(args[0]) + " xp to " + player.getName() + ".");
                    } catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Can't understand this number!");
                    }
                } else if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("setXp")) {
                    try {
                        player.getSkills().get(args[0]).setXp(Math.max(Double.parseDouble(args[2]), 0));
                        player.sendMessage(ChatColor.GREEN + "Set " + args[2] + " " + Functions.setTitleCase(args[0]) + " xp to " + player.getName() + ".");
                    } catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Can't understand this number!");
                    }
                } else if (args[1].equalsIgnoreCase("setLevel") || args[1].equalsIgnoreCase("setLVL")) {
                    try {
                        int level = Functions.range(Integer.parseInt(args[2]), 0, player.getSkills().get(args[0]).getMaxLevel());
                        player.getSkills().get(args[0]).setLevel(level);
                        player.sendMessage(ChatColor.GREEN + "Set " + args[2] + " " + Functions.setTitleCase(args[0]) + " level to " + player.getName() + ".");
                    } catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Can't understand this number!");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Can't understand the skill " + args[0] + "!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Invalid arguments! use /skilladmin <skill> give/set/setLevel/setLVL <number> [<player>]");
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", "farming", "mining", "combat", "foraging", "fishing", "enchanting", "alchemy", "taming", "dungeoneering"));
        tabs.add(new Argument(1, "", "give", "set", "setLevel", "setLVL"));
//        tabs.add(new Argument(2, "", "10", "100", "1000"));
        tabs.add(new Argument(3, "", Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList())));
        return tabs;
    }
}
