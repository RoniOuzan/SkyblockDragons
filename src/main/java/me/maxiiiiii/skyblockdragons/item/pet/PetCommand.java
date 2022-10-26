package me.maxiiiiii.skyblockdragons.item.pet;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.PetModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class PetCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
            PetMaterial petMaterial = player.getActivePetMaterial();
            if (args[0].equalsIgnoreCase("xp")) {
                Item pet = player.getActivePet();
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("give") && args.length > 2) {
                        pet.getModifiers().getPet().giveXp(Double.parseDouble(args[2]));
                        player.updatePlayerInventory();
                        player.getPlayerPet().updateActivePet();
                    } else if (args[1].equalsIgnoreCase("set") && args.length > 2) {
                        pet.getModifiers().getPet().setCurrentXp(Double.parseDouble(args[2]));
                        player.getPlayerPet().updateActivePet();
                    } else if (args[1].equalsIgnoreCase("levelUp")) {
                        pet.getModifiers().getPet().setCurrentXp(petMaterial.getNeedXp(pet.getModifiers().getPet().getLevel()));
                        player.getPlayerPet().updateActivePet();
                    } else if (args[1].equalsIgnoreCase("level") && args.length > 2) {
                        int level = Integer.parseInt(args[2]);
                        pet.getModifiers().getPet().setCurrentXp(0);
                        pet.getModifiers().getPet().setLevel(level);
                        player.getPlayerPet().updateActivePet();
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "Your pet has " + Functions.getNumberFormat(pet.getModifiers().getPet().getCurrentXp()) + " xp.");
                }
            } else {
                if (!Items.pets.containsKey(args[0])) {
                    player.sendMessage(ChatColor.RED + "Can't understand this pet!");
                    return;
                }
                int rarity = args.length > 1 ? Integer.parseInt(args[1]) : 0;
                rarity = Math.max(Math.min(rarity, petMaterial.getAbilities().size()) - 1, 0);
                int level = args.length > 2 ? Integer.parseInt(args[2]) : 1;
                level = Math.max(Math.min(level, petMaterial.getMaxLevel()), 1);
                double currentXp = args.length > 3 ? Double.parseDouble(args[3]) : 0;

                Item pet = new Item(player, petMaterial, new PetModifier(new PetSupplier(Rarity.getRarity(rarity), level, currentXp)));

                player.getInventory().addItem(pet);
            }
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", Items.pets.values().stream().map(ItemMaterial::name).collect(Collectors.toList())));
        return tabs;
    }
}
