package me.maxiiiiii.skyblockdragons.pet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PetCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (!PetMaterial.isPetMaterial(args[0])) {
                    player.sendMessage(ChatColor.RED + "Can't understand this pet!");
                    return true;
                }

                PetMaterial petMaterial = PetMaterial.Pets.get(args[0].toUpperCase());
                ItemStack pet = new Pet(petMaterial, petMaterial.getRarities().get(0), 1, 0).toItem(true);

                player.getInventory().addItem(pet);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();
        for (PetMaterial pet : PetMaterial.Pets.values()) {
            if (!pet.name().equals("NULL")) {
                tabs.add(pet.name());
            }
        }
        return tabs;
    }
}
