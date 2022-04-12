package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class MiningMaterial extends ToolMaterial implements ItemStatsAble {
    private final Stats stats;
    private final int breakingPower;

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, int breakingPower, String description, List<ItemAbility> abilities) {
        super(material, family, name, type, rarity, id, nbt, 0, description, abilities);
        this.stats = stats;
        this.breakingPower = breakingPower;
    }

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, int breakingPower, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", stats, breakingPower, description, new ArrayList<>(Arrays.asList(abilities)));
    }

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, int breakingPower, String description, ItemAbility... abilities) {
        this(material, family, name, type, rarity, "", "", new Stats(), breakingPower, description, new ArrayList<>(Arrays.asList(abilities)));
    }
}
