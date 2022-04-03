package me.maxiiiiii.skyblockdragons.item.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.getItemMaterial;

public class ViewRecipe implements CommandExecutor, Listener, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                if (Recipe.recipes.values().stream().map(Recipe::name).collect(Collectors.toList()).contains(args[0].toUpperCase())) {
                    Recipe recipe = Recipe.get(args[0].toUpperCase());
                    if (recipe.isViewable()) {
                        recipe.view((Player) sender);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Cannot find this recipe!");
                    }
                } else {
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
        List<String> tab = Recipe.recipes.values().stream().map(Recipe::name).filter(s -> s.startsWith(args[0].toUpperCase())).collect(Collectors.toList());
        tab.addAll(Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList()));
        return tab;

    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getInventory().getTitle().contains(" Recipe")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back") ||
                    e.getCurrentItem().getItemMeta().getDisplayName().contains("Close") ||
                    e.getCurrentItem().getItemMeta().getDisplayName().contains("Previous Page") ||
                    e.getCurrentItem().getItemMeta().getDisplayName().contains("Next Page")
            )
                return;
            Recipe recipe = Recipe.get(getItemMaterial(e.getCurrentItem()).name());
            if (recipe != null)
                recipe.view((Player) e.getWhoClicked());
        }
    }
}
