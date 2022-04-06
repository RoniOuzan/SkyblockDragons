package me.maxiiiiii.skyblockdragons.item.craftingtable.commands;

import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RecipesForCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!Functions.isNotAir(player.getEquipment().getItemInMainHand())) {
                player.sendMessage(ChatColor.RED + "You have to hold an item to use this command!");
                return true;
            }
            RecipesMenu.openRecipesTypeFor(player, player.getEquipment().getItemInMainHand(), 1);
        }
        return true;
    }
}
