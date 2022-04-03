package me.maxiiiiii.skyblockdragons.item.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RecipesCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            RecipesMenu.openRecipes(player);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (!e.getClickedInventory().getTitle().contains("Recipe Book")) return;

        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getTitle().contains(" - Page")) {
            if (e.getSlot() != 53 && e.getSlot() != 45)
                return;

            int page = Integer.parseInt(e.getClickedInventory().getTitle().split("Page ")[1]);
            String type = e.getClickedInventory().getTitle().split(" Recipe Book")[0];

            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Next Page")) {
                page++;
            } else {
                page--;
            }

            RecipesMenu.openRecipesType(player, RecipesMenu.Type.valueOf(type.toUpperCase()), page);
        } else {
            String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().replace(" Recipes", ""));
            RecipesMenu.openRecipesType(player, RecipesMenu.Type.valueOf(name.toUpperCase()), 1);
        }
    }
}
