package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.skill.SkillMenu;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeMenu;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.maxiiiiii.skyblockdragons.menu.SkyblockMenu.*;

public class JavaMenu implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (args[0].equals("profile")) {
                    openSkyblockProfileMenu(player);
                }
            } else {
                openSkyblockMenu(player);
            }
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Close") && e.getCurrentItem().getType() == Material.BARRIER) {
                e.getWhoClicked().closeInventory();
            }
            if (e.getClickedInventory().getTitle().contains("Skyblock Menu") || e.getClickedInventory().getTitle().contains("Your Skyblock Profile")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Your Skyblock Profile")) {
                    openSkyblockProfileMenu((Player) e.getWhoClicked());
                }
            }

            if (e.getClickedInventory().getTitle().contains("Skyblock Menu")) {
                Player player = (Player) e.getWhoClicked();
                if (e.getSlot() == 19) {
                    player.closeInventory();
                    SkillMenu.openSkillsMenu(player);
                } else if (e.getSlot() == 31) {
                    player.closeInventory();
                    CraftingTableMenu.openCraftingTable(player);
                } else if (e.getSlot() == 32) {
                    player.closeInventory();
                    WardrobeMenu.openWardrobe(player, 1);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
