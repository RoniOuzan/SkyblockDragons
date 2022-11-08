package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommandSD implements TabExecutor {
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

        public Argument(int index, String string, List<?> tabs) {
            this.index = index;
            this.text = string;
            this.tabs = tabs.stream().map(Object::toString).collect(Collectors.toList());
            this.tabs.remove("");
        }

        public Argument(int index, String string, Set<String> tabs) {
            this(index, string, new ArrayList<>(tabs));
        }

        public Argument(int index, String string, String... tabs) {
            this(index, string, Arrays.asList(tabs));
        }

        public Argument(int index, String string, boolean sortTabs, String... tabs) {
            this(index, string, sortTabs ? Arrays.stream(tabs).sorted().collect(Collectors.toList()) : Arrays.asList(tabs));
        }

        @Override
        public String toString() {
            return "Argument{" +
                    "index=" + index +
                    ", text='" + text + '\'' +
                    ", tabs=" + tabs +
                    '}';
        }
    }

    public abstract List<Argument> tabComplete(PlayerSD player, List<Argument> tabs);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player))
            return null;
//        SkyblockDragons.logger.info("onTabComplete CommandSD called with player " + sender.getName() + ", alias " + alias + ", args " + Arrays.toString(args));
        List<Argument> arguments = this.tabComplete(SkyblockDragons.getPlayer((Player) sender), new ArrayList<>());
        if (arguments == null)
            return null;
        List<List<String>> tabs = arguments.stream()
                .filter(a -> a.index == args.length - 1 && (a.index <= 0 || a.text.startsWith(args[a.index - 1])))
                .map(a -> a.tabs)
                .collect(Collectors.toList());
        if (tabs.size() > 0) {
            return tabs.get(0).stream().filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
        }
        return null;
    }
}
