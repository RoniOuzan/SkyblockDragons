package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

@Getter
@Setter
public abstract class BowMaterial extends WeaponMaterial {
    public BowMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, MaterialModifier... modifiers) {
        super(material, family, name, ItemType.BOW, rarity, id, nbt, sellPrice, stats, description, modifiers);
    }

    public BowMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        this(material, family, name, rarity, "", "", 0, stats, description, modifiers);
    }
}
