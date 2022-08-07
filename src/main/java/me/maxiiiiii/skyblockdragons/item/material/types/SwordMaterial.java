package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import org.bukkit.Material;

@Getter
@Setter
public class SwordMaterial extends WeaponMaterial {
    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, Object... objects) {
        super(material, family, name, ItemType.SWORD, rarity, id, nbt, sellPrice, stats, description, objects);
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, Stats stats, String description, Object... objects) {
        this(material, family, name, rarity, id, nbt, 0, stats, description, objects);
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, Object... objects) {
        this(material, family, name, rarity, "", "", 0, stats, description, objects);
    }
}
