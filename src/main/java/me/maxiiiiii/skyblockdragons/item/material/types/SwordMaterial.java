package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

@Getter
@Setter
public abstract  class SwordMaterial extends WeaponMaterial {
    public SwordMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, ItemType.SWORD, rarity, stats, description, modifiers);
    }
}
