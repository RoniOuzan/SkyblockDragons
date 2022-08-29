package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CommandSD implements CommandExecutor, TabCompleter {
    public abstract void command(PlayerSD player, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            this.command(SkyblockDragons.getPlayer((Player) sender), args);
        }
        return true;
    }

    public static class Argument {
        private final int index;
        private final String text;
        private final List<String> tabs;

        public Argument(int index, String string, List<String> tabs) {
            this.index = index;
            this.text = string;
            this.tabs = tabs;
        }

        public Argument(int index, String string, Set<String> tabs) {
            this(index, string, new ArrayList<>(tabs));
        }

        public Argument(int index, String string, String... tabs) {
            this(index, string, Arrays.asList(tabs));
        }
    }

    public abstract List<Argument> tabComplete(List<Argument> tabs);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<Argument> arguments = this.tabComplete(new ArrayList<>());
        if (arguments == null)
            return null;
        List<List<String>> tabs = arguments.stream()
                .filter(a -> a.index == args.length - 1 && (a.index <= 0 || args[a.index - 1].startsWith(a.text)))
                .map(a -> a.tabs)
                .collect(Collectors.toList());
        if (tabs.size() > 0) {
            return tabs.get(0).stream().filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
        }
        return null;
    }
}
