package me.maxiiiiii.skyblockdragons.item.pet;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class PetCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
            PetMaterial petMaterial = Items.pets.get(args[0].toUpperCase());
            if (args[0].equalsIgnoreCase("xp")) {
                Pet pet = player.getPetActive();
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("give") && args.length > 2) {
                        pet.setCurrentXp(pet.getCurrentXp() + Double.parseDouble(args[2]));
                        pet.update();
                        player.getPlayerPet().pets.set(player.getPlayerPet().activePet, player.getPetActive());
                    } else if (args[1].equalsIgnoreCase("set") && args.length > 2) {
                        pet.setCurrentXp(Double.parseDouble(args[2]));
                        pet.update();
                        player.getPlayerPet().pets.set(player.getPlayerPet().activePet, player.getPetActive());
                    } else if (args[1].equalsIgnoreCase("levelup")) {
                        pet.setCurrentXp(pet.getNeedXp());
                        pet.update();
                        player.getPlayerPet().pets.set(player.getPlayerPet().activePet, player.getPetActive());
                    } else if (args[1].equalsIgnoreCase("level") && args.length > 2) {
                        int level = Integer.parseInt(args[2]);
                        pet.setCurrentXp(0);
                        pet.setLevel(level);
                        pet.update();
                        player.getPlayerPet().pets.set(player.getPlayerPet().activePet, player.getPetActive());
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "Your pet has " + Functions.getNumberFormat(pet.getCurrentXp()) + " xp.");
                }
            } else {
                if (!Items.pets.containsKey(args[0])) {
                    player.sendMessage(ChatColor.RED + "Can't understand this pet!");
                    return;
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

    @Override
    public List<Argument> tabComplete(List<Argument> tabs) {
        tabs.add(new Argument(0, "", Items.pets.values().stream().map(ItemMaterial::name).collect(Collectors.toList())));
        return tabs;
    }
}
