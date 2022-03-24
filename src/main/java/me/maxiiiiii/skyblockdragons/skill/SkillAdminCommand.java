package me.maxiiiiii.skyblockdragons.skill;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 2) {
                if (Functions.isSkillName(args[0])) {
                    Player player = (Player) sender;
                    if (args.length > 3 && Functions.isPlayerName(args[3])) {
                        player = Bukkit.getPlayerExact(args[3]);
                    }
                    PlayerSD PlayerSD = SkyblockDragons.players.get(player.getUniqueId());
                    if (args[1].equalsIgnoreCase("give")) {
                        try {
                            double amount = Double.parseDouble(args[2]);
                            PlayerSD.getSkill().get(args[0]).giveXp(amount, player);
                            sender.sendMessage(ChatColor.GREEN + "Gave " + args[2] + " " + Functions.setTitleCase(args[0]) + " xp to " + player.getName() + ".");
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(ChatColor.RED + "Can't understand this number!");
                        }
                    } else if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("setXp")) {
                        try {
                            double amount = Double.parseDouble(args[2]);
                            PlayerSD.getSkill().get(args[0]).setXp(amount, player);
                            sender.sendMessage(ChatColor.GREEN + "Setted " + args[2] + " " + Functions.setTitleCase(args[0]) + " xp to " + player.getName() + ".");
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(ChatColor.RED + "Can't understand this number!");
                        }
                    } else if (args[1].equalsIgnoreCase("setLevel") || args[1].equalsIgnoreCase("setLVL")) {
                        try {
                            int level = Integer.parseInt(args[2]);
                            PlayerSD.getSkill().get(args[0]).setLevel(level, player);
                            sender.sendMessage(ChatColor.GREEN + "Setted " + args[2] + " " + Functions.setTitleCase(args[0]) + " level to " + player.getName() + ".");
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(ChatColor.RED + "Can't understand this number!");
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Can't understand the skill " + args[0] + "!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid arguments!");
            }
        }
        return true;
    }
}
