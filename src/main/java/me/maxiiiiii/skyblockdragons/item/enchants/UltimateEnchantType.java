package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;

import java.util.ArrayList;

public class UltimateEnchantType extends EnchantType {
    UltimateEnchantType(Stats stats, int maxLevel, String description, EnchantMultiplayer multiplayer, SkillRequirement requirement, ArrayList<ItemType> types, String distraction) {
        super(stats, maxLevel, description, multiplayer, requirement, types, getAllDistractions(distraction), false);
    }

    UltimateEnchantType(int maxLevel, String description, EnchantMultiplayer multiplayer, SkillRequirement requirement, ArrayList<ItemType> types, String distraction) {
        this(new Stats(), maxLevel, description, multiplayer, requirement, types, distraction);
    }

    private static ArrayList<String> getAllDistractions(String distraction) {
        ArrayList<String> distractions = new ArrayList<>();
        for (EnchantType enchantType : enchants.values()) {
            if (enchantType instanceof UltimateEnchantType) {
                if (enchantType.name().equals(distraction.toUpperCase())) continue;
                distractions.add(enchantType.name());
            }
        }
        return distractions;
    }
}
