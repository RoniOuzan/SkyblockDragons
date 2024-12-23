package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.ToString;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
public abstract class ItemMaterial implements ConfigurationSerializable, Comparable<ItemMaterial>, Listener {
    protected String itemID;
    
    protected Material material;
    protected ItemFamily family;
    protected String name;
    protected ItemType type;
    protected Rarity rarity;
    protected int data;

    public ItemMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity) {
        this.itemID = itemID;
        this.material = material;
        this.family = family;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.data = 0;

        Bukkit.getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public ItemSkull getItemSkull() {
        return new ItemSkull("", "");
    }

    public int getData() {
        return this.data;
    }

    public String getName() {
        return Functions.setTitleCase(this.name.replaceAll("_", " "));
    }

    public String name() {
        if (this.itemID.equals(QuickMaterial.itemID)) {
            for (String key : Items.items.keySet()) {
                if (Items.items.get(key) == this) {
                    return key;
                }
            }
            return "NULL";
        }

        return this.itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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

    @Override
    public int compareTo(ItemMaterial itemMaterial) {
        return this.name().compareTo(itemMaterial.name());
    }
}