package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class AccessoryMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble {
    public static final AccessoryMaterial NULL = new AccessoryMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");

    private final Stats stats;
    private final String description;

    public AccessoryMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, Stats stats, String description) {
        super(material, family, name, ItemType.ACCESSORY, rarity, id, nbt, 0);
        this.stats = stats;
        this.description = description;
    }
}
