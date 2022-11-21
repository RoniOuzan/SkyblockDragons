package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public abstract class RangeWeaponMaterial extends WeaponMaterial {
    public RangeWeaponMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, stats, description, modifiers);
    }
}
