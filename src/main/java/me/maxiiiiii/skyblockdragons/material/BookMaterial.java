package me.maxiiiiii.skyblockdragons.material;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.Arrays;

public class BookMaterial extends ToolMaterial implements ConfigurationSerializable {
    public BookMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt) {
        super(material, family, name, type, rarity, id, nbt, 0, "", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }
}
