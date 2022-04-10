package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class BowMaterial extends WeaponMaterial {
    public BowMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, List<ItemAbility> abilities) {
        super(material, family, name, ItemType.BOW, rarity, id, nbt, sellPrice, stats, description, abilities);
    }

    public BowMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, id, nbt, sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public BowMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, id, nbt, 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public BowMaterial(Material material, ItemFamily family, String name, Rarity rarity, double sellPrice, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, "", "", sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public BowMaterial(Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, "", "", 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }
}
