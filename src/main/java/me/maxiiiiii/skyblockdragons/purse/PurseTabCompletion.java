package me.maxiiiiii.skyblockdragons.purse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurseTabCompletion implements TabCompleter {
    private static final String[] tabList = {"add", "give", "info", "send", "set"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length > 1) {
            for (String tab : tabList) {
                if (tab.startsWith(args[1])) {
                    tabs.add(tab);
                }
            }

            Collections.sort(tabs);

            return tabs;
        }
        return tabs;
    }
}
