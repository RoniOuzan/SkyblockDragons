package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ToolMaterial extends ItemMaterial {
    public static final ToolMaterial NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", "", new ItemAbility(AbilityAction.NONE, "", "", 0, 0));

    private String description;
    private List<ItemAbility> abilities;

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, List<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt);
        this.description = description;
        this.abilities = abilities == null || abilities.size() == 0 ? new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))) : abilities;
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, ItemAbility ability) {
        this(material, family, name, type, rarity, id, nbt, description, new ArrayList<>(Arrays.asList(ability)));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, ArrayList<ItemAbility> abilities) {
        this(material, family, name, type, rarity, "", "", description, abilities);
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, ItemAbility ability) {
        this(material, family, name, type, rarity, "", "", description, new ArrayList<>(Arrays.asList(ability)));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description) {
        this(material, family, name, type, rarity, "", "", description, new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description) {
        this(material, family, name, type, rarity, id, nbt, description, new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }
}
