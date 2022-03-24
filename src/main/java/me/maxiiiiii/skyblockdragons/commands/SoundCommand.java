package me.maxiiiiii.skyblockdragons.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SoundCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                float pitch = 1f;
                if (args.length > 1) {
                    pitch = Float.parseFloat(args[1]);
                }
                player.playSound(player.getLocation(), Sound.valueOf(args[0]), 1, pitch);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();
        for (Sound sound : Sound.values()) {
            if (sound.name().contains(args[0].toUpperCase()))
                tabs.add(sound.name());
        }
        return tabs;
    }
}
