package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.item.objects.ItemStats;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;

import java.util.ArrayList;

public class UltimateEnchantType extends EnchantType {
    UltimateEnchantType(ItemStats stats, int maxLevel, String description, double multiplayer, ArrayList<ItemType> types, String distraction) {
        super(stats, maxLevel, description, multiplayer, types, getAllDistractions(distraction));
    }

    UltimateEnchantType(int maxLevel, String description, double multiplayer, ArrayList<ItemType> types, String distraction) {
        this(new ItemStats(), maxLevel, description, multiplayer, types, distraction);
    }

    private static ArrayList<String> getAllDistractions(String distraction) {
        ArrayList<String> distractions = new ArrayList<>();
        for (EnchantType enchantType : Enchants.values()) {
            if (enchantType instanceof UltimateEnchantType) {
                if (enchantType.name().equals(distraction.toUpperCase())) continue;
                distractions.add(enchantType.name());
            }
        }
        return distractions;
    }
}
