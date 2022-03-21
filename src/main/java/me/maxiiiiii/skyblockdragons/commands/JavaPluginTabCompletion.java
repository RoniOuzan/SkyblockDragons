package me.maxiiiiii.skyblockdragons.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaPluginTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length > 1 && args[0].equalsIgnoreCase("sound")) {
            for (Sound sound : Sound.values()) {
                if (sound.name().contains(args[1].toUpperCase())) {
                    tabs.add(sound.name());
                }
            }
            Collections.sort(tabs);
            return tabs;
        }
        return null;
    }
}
