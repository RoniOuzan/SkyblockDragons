package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.ToString;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;

@Getter
@ToString
public class ItemMaterial {
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
}