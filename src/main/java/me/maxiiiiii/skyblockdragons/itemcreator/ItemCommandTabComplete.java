package me.maxiiiiii.skyblockdragons.itemcreator;

import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCommandTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length == 1) {
            for (ItemMaterial tab : ItemMaterial.Items.values()) {
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
