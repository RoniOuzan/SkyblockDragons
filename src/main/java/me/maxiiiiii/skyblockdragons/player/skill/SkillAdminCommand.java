package me.maxiiiiii.skyblockdragons.player.skill;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkillAdminCommand extends CommandSD {

    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 2) {
            if (Functions.isSkillName(args[0])) {
                List<String> modifiers = new ArrayList<>(Arrays.asList(args).subList(2, args.length));
                PlayerSD target = SkyblockDragons.getPlayer(modifiers.stream().filter(s -> Bukkit.getPlayer(s) != null).findFirst().orElse(player.getName()));
                SkillType skillType = SkillType.valueOf(args[0].toUpperCase());
                if (args[1].equalsIgnoreCase("give")) {
                    try {
                        double amount = Double.parseDouble(args[2]);
                        if (modifiers.contains("-s"))
                            target.getSkills().get(args[0]).giveXp(amount);
                        else {
                            SkillXpSource<?> source = new SkillXpSource<>(SkillXpSourceType.ADMIN, player);
                            if (modifiers.contains("-e"))
                                source = new SkillXpSource<>(SkillXpSourceType.ENTITY, player);
                            target.giveSkill(skillType, amount, source);
                        }
                        player.sendMessage(ChatColor.GREEN + "Gave " + args[2] + " " + Functions.setTitleCase(args[0]) + " xp to " + target.getName() + ".");
                    } catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Can't understand this number!");
                    }
                } else if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("setXp")) {
                    try {
                        target.getSkills().get(args[0]).setXp(Math.max(Double.parseDouble(args[2]), 0));
                        player.sendMessage(ChatColor.GREEN + "Set " + args[2] + " " + Functions.setTitleCase(args[0]) + " xp to " + target.getName() + ".");
                    } catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Can't understand this number!");
                    }
                } else if (args[1].equalsIgnoreCase("setLevel") || args[1].equalsIgnoreCase("setLVL")) {
                    try {
                        int level = Functions.range(Integer.parseInt(args[2]), 0, target.getSkills().get(args[0]).getMaxLevel());
                        target.getSkills().get(args[0]).setLevel(level);
                        player.sendMessage(ChatColor.GREEN + "Set " + args[2] + " " + Functions.setTitleCase(args[0]) + " level to " + target.getName() + ".");
                    } catch (NumberFormatException ex) {
                        player.sendMessage(ChatColor.RED + "Can't understand this number!");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Can't understand the skill " + args[0] + "!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Invalid arguments! use /skilladmin <skill> give/set/setLevel/setLVL <number> [<modifiers...>]");
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
