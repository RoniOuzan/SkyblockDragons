package me.maxiiiiii.skyblockdragons.player.pet;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
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
            PlayerSD player = SkyblockDragons.players.get(((Player) sender).getUniqueId());
            if (args.length > 0) {
                PetMaterial petMaterial = PetMaterial.Pets.get(args[0].toUpperCase());
                if (args[0].equalsIgnoreCase("xp")) {
                    Pet pet = player.getPetActive();
                    if (args.length > 1) {
                        if (args[1].equalsIgnoreCase("give") && args.length > 2) {
                            pet.setCurrentXp(pet.getCurrentXp() + Double.parseDouble(args[2]));
                            pet.update();
                            player.pets.set(player.activePet, player.getPetActive());
                        } else if (args[1].equalsIgnoreCase("set") && args.length > 2) {
                            pet.setCurrentXp(Double.parseDouble(args[2]));
                            pet.update();
                            player.pets.set(player.activePet, player.getPetActive());
                        } else if (args[1].equalsIgnoreCase("levelup")) {
                            pet.setCurrentXp(pet.getNeedXp());
                            pet.update();
                            player.pets.set(player.activePet, player.getPetActive());
                        } else if (args[1].equalsIgnoreCase("level") && args.length > 2) {
                            int level = Integer.parseInt(args[2]);
                            pet.setCurrentXp(0);
                            pet.setLevel(level);
                            pet.update();
                            player.pets.set(player.activePet, player.getPetActive());
                        }
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Your pet has " + Functions.getNumberFormat(pet.getCurrentXp()) + " xp.");
                    }
                } else {
                    if (!PetMaterial.isPetMaterial(args[0])) {
                        player.sendMessage(ChatColor.RED + "Can't understand this pet!");
                        return true;
                    }
                    int rarity = args.length > 1 ? Integer.parseInt(args[1]) : 0;
                    rarity = Math.max(Math.min(rarity, petMaterial.getRarities().size()) - 1, 0);
                    int level = args.length > 2 ? Integer.parseInt(args[2]) : 1;
                    level = Math.max(Math.min(level, petMaterial.getMaxLevel()), 1);
                    double currentXp = args.length > 3 ? Double.parseDouble(args[3]) : 0;

                    ItemStack pet = new Pet(petMaterial, petMaterial.getRarities().get(rarity), level, currentXp, true);

                    player.getInventory().addItem(pet);
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();
        for (PetMaterial pet : PetMaterial.Pets.values()) {
            tabs.add(pet.name());
        }
        return tabs;
    }
}
