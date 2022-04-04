package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ToolMaterial extends ItemMaterial implements ConfigurationSerializable {
    public static final ToolMaterial NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", 0, "", new ItemAbility(AbilityAction.NONE, "", "", 0, 0));

    private String description;
    private List<ItemAbility> abilities;

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, String description, List<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt, sellPrice);
        this.description = description;
        this.abilities = abilities == null || abilities.size() == 0 ? new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))) : abilities;
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, id, nbt, sellPrice, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, id, nbt, 0, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, double sellPrice, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", sellPrice, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", 0, description, new ArrayList<>(Arrays.asList(abilities)));
    }

//    public Map<String, Object> serialize() {
//        Map<String, Object> map = super.serialize();
//        map.put("description", description);
//        map.put("ability", abilities);
//        return map;
//    }
}
