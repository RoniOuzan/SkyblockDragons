package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemFullSet;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class ArmorMaterial extends ItemMaterial {
    public static final ArmorMaterial NULL = new ArmorMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", ItemFullSet.NULL, Color.RED);

    private ArrayList<Integer> stats;
    private String description;
    private ItemFullSet fullSet;
    private Color color;

    ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ItemFullSet fullSet, Color color) {
        super(material, family, name, type, rarity, id, nbt);
        this.stats = stats;
        this.description = description;
        this.fullSet = fullSet;
        this.color = color;
    }

    ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ItemFullSet fullSet) {
        this(material, family, name, type, rarity, id, nbt, stats, description, fullSet, Color.BLACK);
    }

    ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, ArrayList<Integer> stats, String description, ItemFullSet fullSet, Color color) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, color);
    }
}
