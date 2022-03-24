package me.maxiiiiii.skyblockdragons.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.craftingtable.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.getItemMaterial;

public class ViewRecipe implements CommandExecutor, Listener, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                try {
                    Recipe recipe = Recipe.valueOf(args[0].toUpperCase());
                    if (recipe.isViewable()) {
                        recipe.view((Player) sender);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Cannot find this recipe!");
                    }
                } catch (IllegalArgumentException ex) {
                    sender.sendMessage(ChatColor.RED + "Cannot find this recipe!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid arguments!");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();
        for (Recipe recipe : Recipe.values()) {
            tabs.add(recipe.name());
        }
        return tabs;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains(" Recipe")) {
            e.setCancelled(true);
            try {
                Recipe recipe = Recipe.valueOf(getItemMaterial(e.getCurrentItem()).name());
                recipe.view((Player) e.getWhoClicked());
            } catch (IllegalArgumentException ignored) {}
        }
    }
}
