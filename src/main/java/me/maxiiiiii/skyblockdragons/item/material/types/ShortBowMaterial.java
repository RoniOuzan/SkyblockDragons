package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public abstract class ShortBowMaterial extends RangeWeaponMaterial {
    public ShortBowMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, ItemType.SHORT_BOW, rarity, stats, description, modifiers);
    }
}
