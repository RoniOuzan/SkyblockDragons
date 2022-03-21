package me.maxiiiiii.skyblockdragons.pet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class PetTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();
        for (PetMaterial pet : PetMaterial.values()) {
            if (!pet.name().equals("NULL")) {
                tabs.add(pet.name());
            }
        }
        return tabs;
    }
}
