package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemStats;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

@Getter
public class AccessoryMaterial extends ItemMaterial implements ConfigurationSerializable {
    public static final AccessoryMaterial NULL = new AccessoryMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", new ItemStats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");

    private final ItemStats stats;
    private final String description;

    public AccessoryMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, ItemStats stats, String description) {
        super(material, family, name, ItemType.ACCESSORY, rarity, id, nbt, 0);
        this.stats = stats;
        this.description = description;
    }

//    public Map<String, Object> serialize() {
//        Map<String, Object> map = super.serialize();
//        map.put("stats", stats);
//        map.put("description", description);
//        return map;
//    }
}
