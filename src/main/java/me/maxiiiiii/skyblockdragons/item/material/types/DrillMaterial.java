package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrillMaterial extends MiningMaterial {
    public DrillMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, Stats stats, int breakingPower, String description, List<ItemAbility> abilities) {
        super(material, family, name, ItemType.DRILL, rarity, id, nbt, stats, breakingPower, description, abilities);
    }

    public DrillMaterial(Material material, ItemFamily family, String name, Rarity rarity, Stats stats, int breakingPower, String description, ItemAbility... abilities) {
        this(material, family, name, rarity, "", "", stats, breakingPower, description, new ArrayList<>(Arrays.asList(abilities)));
    }
}
