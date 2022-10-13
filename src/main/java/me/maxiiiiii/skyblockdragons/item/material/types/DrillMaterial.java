package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

public class DrillMaterial extends MiningMaterial {
    public DrillMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, Stats stats, int breakingPower, String description, MaterialModifier... modifiers) {
        super(material, family, name, ItemType.SWORD, rarity, id, nbt, stats, breakingPower, description, modifiers);
    }

    public DrillMaterial(Material material, ItemFamily family, String name, Rarity rarity, Stats stats, int breakingPower, String description, MaterialModifier... modifiers) {
        this(material, family, name, rarity, "", "", stats, breakingPower, description, modifiers);
    }
}
