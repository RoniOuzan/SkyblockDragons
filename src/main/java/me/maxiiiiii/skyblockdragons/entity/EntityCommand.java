package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class EntityCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                new EntitySD(player.getLocation(), EntityMaterial.get(args[0]));
                player.sendMessage(ChatColor.GREEN + "Summoned new " + Functions.setTitleCase(args[0]) + ".");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return EntityMaterial.Entities.values().stream().map(EntityMaterial::name).filter(s -> s.startsWith(args[0].toUpperCase())).collect(Collectors.toList());
    }
}
