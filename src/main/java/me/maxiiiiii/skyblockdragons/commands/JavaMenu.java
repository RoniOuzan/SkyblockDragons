package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.player.bank.BankMenu;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.pet.PetMenu;
import me.maxiiiiii.skyblockdragons.player.skill.SkillMenu;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeMenu;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.maxiiiiii.skyblockdragons.commands.menu.SkyblockMenu.*;

public class JavaMenu implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
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
        if (!Functions.isNotAir(e.getCurrentItem()) || !e.getCurrentItem().hasItemMeta()) return;

        if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Close") && e.getCurrentItem().getType() == Material.BARRIER) {
            e.getWhoClicked().closeInventory();
        }
        if (e.getClickedInventory().getTitle().contains("Skyblock Menu") || e.getClickedInventory().getTitle().contains("Your Skyblock Profile")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Your Skyblock Profile")) {
                openSkyblockProfileMenu(SkyblockDragons.getPlayer((Player) e.getWhoClicked()));
            }
        }

        if (e.getClickedInventory().getTitle().contains("Skyblock Menu")) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Your Skills")) {
                player.closeInventory();
                SkillMenu.openSkillsMenu(player);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Pets")) {
                player.closeInventory();
                PetMenu.openPetMenu(player, 1);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Crafting Table")) {
                player.closeInventory();
                CraftingTableMenu.openCraftingTable(player);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Wardrobe")) {
                player.closeInventory();
                WardrobeMenu.openWardrobe(player, 1);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Bank")) {
                player.closeInventory();
                BankMenu.openBank(player);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Recipe Book")) {
                player.closeInventory();
                RecipesMenu.openRecipes(player);
            }
        }
    }
}
