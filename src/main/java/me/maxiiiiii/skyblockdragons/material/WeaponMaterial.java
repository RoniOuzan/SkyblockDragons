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
import java.util.Map;

@Getter
@Setter
public class WeaponMaterial extends ToolMaterial implements ConfigurationSerializable {
    private ItemStats stats;
    private String description;

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, ItemStats stats, String description, List<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt, sellPrice, description, abilities);
        this.stats = stats;
        this.description = description;
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, id, nbt, sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, id, nbt, 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, double sellPrice, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", sellPrice, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, ItemStats stats, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", 0, stats, description, new ArrayList<>(Arrays.asList(abilities)));
    }

//    public Map<String, Object> serialize() {
//        Map<String, Object> map = super.serialize();
//        map.put("stats", stats);
//        map.put("description", description);
//        return map;
//    }
}
