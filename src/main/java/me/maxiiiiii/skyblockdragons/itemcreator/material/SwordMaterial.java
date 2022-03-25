package me.maxiiiiii.skyblockdragons.itemcreator.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class SwordMaterial extends WeaponMaterial {
    public static final BowMaterial NULL = new BowMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", new ItemStats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NONE, "", "", 0, 0))));

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, ItemStats stats, String description, ArrayList<ItemAbility> abilities) {
        super(material, family, name, ItemType.SWORD, rarity, id, nbt, stats, description, abilities);
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ItemStats stats, String description, ArrayList<ItemAbility> abilities) {
        this(material, family, name, rarity, "", "", stats, description, abilities);
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, ItemStats stats, String description, ItemAbility ability) {
        this(material, family, name, rarity, "", "", stats, description, new ArrayList<>(Arrays.asList(ability)));
    }

    public SwordMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, ItemStats stats, String description, ItemAbility ability) {
        this(material, family, name, rarity, id, "", stats, description, new ArrayList<>(Arrays.asList(ability)));
    }
}
