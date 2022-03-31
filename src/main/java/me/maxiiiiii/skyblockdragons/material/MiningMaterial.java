package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class MiningMaterial extends ToolMaterial {
    private final ItemStats stats;
    private final int breakingPower;

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ItemStats stats, int breakingPower, String description, List<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt, description, abilities);
        this.stats = stats;
        this.breakingPower = breakingPower;
    }

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, ItemStats stats, int breakingPower, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", stats, breakingPower, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, int breakingPower, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", new ItemStats(), breakingPower, description, new ArrayList<>(Arrays.asList(abilities)));
    }
}
