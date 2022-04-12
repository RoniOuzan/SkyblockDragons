package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

@Getter
@Setter
public class ArmorMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble, ItemEnchantAble {
    public static final ArmorMaterial NULL = new ArmorMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", ItemFullSet.NULL, Color.RED);

    private Stats stats;
    private String description;
    private ItemFullSet fullSet;
    private Color color;

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemFullSet fullSet, Color color) {
        super(material, family, name, type, rarity, id, nbt, 0);
        this.stats = stats;
        this.description = description;
        this.fullSet = fullSet == null ? ItemFullSet.NULL : fullSet;
        this.color = color;
    }

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemFullSet fullSet) {
        this(material, family, name, type, rarity, id, nbt, stats, description, fullSet, Color.BLACK);
    }

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet, Color color) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, color);
    }

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, Color.BLACK);
    }
}
