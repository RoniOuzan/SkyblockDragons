package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

public abstract class DrillMaterial extends MiningMaterial {
    public DrillMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, int breakingPower, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, ItemType.SWORD, rarity, stats, breakingPower, description, modifiers);
    }
}
