package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.itemcreator.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class ToolMaterial extends ItemMaterial {
    public static final ToolMaterial NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", "", new ItemAbility(AbilityAction.NONE, "", "", 0, 0));

    private String description;
    private ArrayList<ItemAbility> abilities;

    ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, ArrayList<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt);
        this.description = description;
        this.abilities = abilities;
    }

    ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, ItemAbility ability) {
        this(material, family, name, type, rarity, id, nbt, description, new ArrayList<>(Arrays.asList(ability)));
    }

    ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, ArrayList<ItemAbility> abilities) {
        this(material, family, name, type, rarity, "", "", description, abilities);
    }

    ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, ItemAbility ability) {
        this(material, family, name, type, rarity, "", "", description, new ArrayList<>(Arrays.asList(ability)));
    }

    ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description) {
        this(material, family, name, type, rarity, "", "", description, new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }

    ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description) {
        this(material, family, name, type, rarity, id, nbt, description, new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }
}
