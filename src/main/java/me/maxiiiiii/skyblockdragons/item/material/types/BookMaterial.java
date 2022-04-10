package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class BookMaterial extends ToolMaterial {
    public BookMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt) {
        super(material, family, name, type, rarity, id, nbt, 0, "", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }
}
