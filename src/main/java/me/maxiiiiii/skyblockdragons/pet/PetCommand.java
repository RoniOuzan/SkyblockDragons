package me.maxiiiiii.skyblockdragons.pet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (!PetMaterial.isPetMaterial(args[0])) {
                    player.sendMessage(ChatColor.RED + "Can't understand this pet!");
                    return true;
                }

                PetMaterial petMaterial = PetMaterial.valueOf(args[0].toUpperCase());
                ItemStack pet = new Pet(petMaterial, petMaterial.getRarities().get(0), 1, 0).toItem(true);

                player.getInventory().addItem(pet);
            }
        }
        return true;
    }
}
