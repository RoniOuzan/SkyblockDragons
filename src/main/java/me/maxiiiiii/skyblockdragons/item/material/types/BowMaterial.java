package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

@Getter
@Setter
public abstract class BowMaterial extends RangeWeaponMaterial {
    public BowMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, ItemType.BOW, rarity, stats, description, modifiers);
    }
}
