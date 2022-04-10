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
public class WeaponMaterial extends ToolMaterial {
    private Stats stats;
    private String description;

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, List<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt, sellPrice, description, abilities);
        this.stats = stats;
        this.description = description;
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, id, nbt, sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, id, nbt, 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, double sellPrice, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }
}
