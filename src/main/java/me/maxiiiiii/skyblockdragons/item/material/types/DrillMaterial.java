package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public abstract class DrillMaterial extends MiningMaterial {
    public DrillMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, int breakingPower, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, ItemType.DRILL, rarity, stats, breakingPower, description, modifiers);
    }
}
