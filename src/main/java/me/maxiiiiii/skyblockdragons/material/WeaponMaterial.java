package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.itemcreator.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class WeaponMaterial extends ToolMaterial {
    public static final WeaponMaterial NULL = new WeaponMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NONE, "", "", 0, 0))));

    private ArrayList<Integer> stats;
    private String description;

    WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ArrayList<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt, description, abilities);
        this.stats = stats;
        this.description = description;
    }

    WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ItemAbility ability) {
        this(material, family, name, type, rarity, id, nbt, stats, description, new ArrayList<>(Arrays.asList(ability)));
    }

    WeaponMaterial(Material material, String name, ItemType type, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ArrayList<ItemAbility> abilities) {
        this(material, ItemFamily.NULL, name, type, rarity, id, nbt, stats, description, abilities);
    }

    WeaponMaterial(Material material, String name, ItemType type, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ItemAbility ability) {
        this(material, ItemFamily.NULL, name, type, rarity, id, nbt, stats, description, new ArrayList<>(Arrays.asList(ability)));
    }

    WeaponMaterial(Material material, String name, ItemType type, Rarity rarity, ArrayList<Integer> stats, String description, ArrayList<ItemAbility> abilities) {
        this(material, ItemFamily.NULL, name, type, rarity, "", "", stats, description, abilities);
    }

    public WeaponMaterial(Material material, String name, ItemType type, Rarity rarity, ArrayList<Integer> stats, String description, ItemAbility ability) {
        this(material, ItemFamily.NULL, name, type, rarity, "", "", stats, description, new ArrayList<>(Arrays.asList(ability)));
    }
}
