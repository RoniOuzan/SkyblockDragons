package me.maxiiiiii.skyblockdragons.itemcreator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookCommandTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length == 1) {
            for (EnchantType tab : EnchantType.Enchants.values()) {
                if (tab.name().contains("NULL")) continue;
                if (tab.name().startsWith(args[0].toUpperCase())) {
                    tabs.add(tab.name());
                }
            }
            Collections.sort(tabs);
            return tabs;
        }
        return null;
    }
}
