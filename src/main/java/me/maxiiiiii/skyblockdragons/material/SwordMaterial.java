package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SwordMaterial extends WeaponMaterial implements ConfigurationSerializable {
    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, double sellPrice, ItemStats stats, String description, List<ItemAbility> abilities) {
        super(material, family, name, ItemType.SWORD, rarity, id, nbt, sellPrice, stats, description, abilities);
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, double sellPrice, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, id, nbt, sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, id, nbt, 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, double sellPrice, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, "", "", sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, "", "", 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

//    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ItemStats stats, String description, List<ItemAbility> abilities) {
//        this(material, family, name, rarity, "", "", stats, description, abilities);
//    }
//
//    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ItemStats stats, String description, ItemAbility ability) {
//        this(material, family, name, rarity, "", "", stats, description, new ArrayList<>(Arrays.asList(ability)));
//    }
//
//    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, ItemStats stats, String description, ItemAbility ability) {
//        this(material, family, name, rarity, id, "", stats, description, new ArrayList<>(Arrays.asList(ability)));
//    }
//
//    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ItemStats stats, String description, ItemAbility... abilities) {
//        this(material, family, name, rarity, "", "", stats, description, Arrays.stream(abilities).collect(Collectors.toList()));
//    }
}
