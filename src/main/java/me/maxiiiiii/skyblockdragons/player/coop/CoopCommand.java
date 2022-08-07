package me.maxiiiiii.skyblockdragons.player.coop;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoopCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (Coop.getPlayerCoop(player) == null) {
                        new Coop(player);
                        player.sendMessage(ChatColor.GREEN + "You have created a new coop.");
                    } else {
                        TextComponent textComponent = new TextComponent(TextComponent.fromLegacyText(ChatColor.GREEN + "You already have a coop, " + ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK HERE TO LEAVE!"));
                        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/coop acceptCreate"));
                        player.spigot().sendMessage(textComponent);
                    }
                } else if (args[0].equalsIgnoreCase("acceptCreate")) {
                    player.performCommand("coop leave");
                    if (Coop.getPlayerCoop(player) == null)
                        player.performCommand("coop create");
                } else if (args[0].equalsIgnoreCase("join")) {
                    if (args.length > 1) {
                        PlayerSD target = SkyblockDragons.getPlayer(args[1]);
                        Coop coop = Coop.getPlayerCoop(target);
                        if (coop == null) {
                            player.sendMessage(ChatColor.RED + "This player does not have a coop!");
                            return true;
                        }
                        player.sendMessage(ChatColor.GREEN + "You joined " + target.getName() + "'s coop.");
                        coop.add(player);
                    }
                } else if (args[0].equalsIgnoreCase("leave")) {
                    Coop coop = Coop.getPlayerCoop(player);
                    if (coop == null) {
                        player.sendMessage(ChatColor.RED + "You are not in a coop!");
                        return true;
                    }
                    if (coop.leader == player && coop.size() > 1) {
                        player.sendMessage(ChatColor.RED + "You can't leave your coop because you are the leader!");
                        player.sendMessage(ChatColor.RED + "You have to transfer the leader to someone else, run /coop transfer <player>");
                    } else {
                        if (coop.leader == player)
                            player.sendMessage(ChatColor.GREEN + "You deleted your coop.");
                        else
                            player.sendMessage(ChatColor.GREEN + "You left your coop.");
                        Coop.coops.remove(coop.uuid);
                    }
                } else if (args[0].equalsIgnoreCase("transfer")) {
                    Coop coop = Coop.getPlayerCoop(player);
                    if (coop == null) {
                        player.sendMessage(ChatColor.RED + "You are not in a coop!");
                        return true;
                    }
                    if (args.length > 1) {
                        PlayerSD target = SkyblockDragons.getPlayer(args[1]);
                        if (coop.contains(target)) {
                            coop.leader = target;
                            player.sendMessage(ChatColor.GREEN + "You have transferred the leader to " + target.getName() + ".");
                        } else
                            player.sendMessage(ChatColor.RED + "This player is not in your coop!");
                    } else
                        player.sendMessage(ChatColor.RED + "You have to give a player to transfer!");
                } else if (args[0].equalsIgnoreCase("list")) {
                    Coop coop = Coop.getPlayerCoop(player);
                    if (coop == null) {
                        player.sendMessage(ChatColor.RED + "You have to be in a coop to use this command!");
                        return true;
                    }
                    for (PlayerSD playerSD : coop.getPlayers()) {
                        player.sendMessage(ChatColor.GREEN + playerSD.getName());
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs1 = new ArrayList<>(Arrays.asList("create", "join", "leave", "transfer", "list"));
        return Functions.getTabs(args, tabs1);
    }
}
