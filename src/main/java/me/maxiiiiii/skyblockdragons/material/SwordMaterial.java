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
public class SwordMaterial extends WeaponMaterial {
    public static final BowMaterial NULL = new BowMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NONE, "", "", 0, 0))));

    SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description, ArrayList<ItemAbility> abilities) {
        super(material, family, name, ItemType.SWORD, rarity, id, nbt, stats, description, abilities);
    }

    SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ArrayList<Integer> stats, String description, ArrayList<ItemAbility> abilities) {
        this(material, family, name, rarity, "", "", stats, description, abilities);
    }

    SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ArrayList<Integer> stats, String description, ItemAbility ability) {
        this(material, family, name, rarity, "", "", stats, description, new ArrayList<>(Arrays.asList(ability)));
    }

    SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, ArrayList<Integer> stats, String description, ItemAbility ability) {
        this(material, family, name, rarity, id, "", stats, description, new ArrayList<>(Arrays.asList(ability)));
    }
}
