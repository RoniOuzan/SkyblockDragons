package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.ToString;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ItemMaterial implements ConfigurationSerializable, ItemSD {
    protected Material material;
    protected ItemFamily family;
    protected String name;
    protected ItemType type;
    protected Rarity rarity;
    protected String id;
    protected String nbt;
    protected double sellPrice;

    public ItemMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice) {
        this.material = material;
        this.family = family;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.id = id;
        this.nbt = nbt;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return Functions.setTitleCase(this.name.replaceAll("_", " "));
    }

    public static ItemMaterial get(String name) {
        return Items.items.getOrDefault(name, Items.NULL);
    }

    public String name() {
        for (String key : Items.items.keySet()) {
            if (Items.items.get(key) == this) {
                return key;
            }
        }
        return "";
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.name());
        return map;
    }

    public static ItemMaterial deserialize(Map<String, Object> args) {
        return Items.get((String) args.get("id"));
    }
}