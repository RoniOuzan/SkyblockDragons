package me.maxiiiiii.skyblockdragons.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.craftingtable.Recipe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ViewRecipeTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();
        for (Recipe recipe : Recipe.values()) {
            tabs.add(recipe.name());
        }
        return tabs;
    }
}
