package me.maxiiiiii.skyblockdragons.material;

import me.maxiiiiii.skyblockdragons.itemcreator.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class BookMaterial extends ToolMaterial {
    BookMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt) {
        super(material, family, name, type, rarity, id, nbt, "", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.NULL, "", "", 0, 0))));
    }
}
