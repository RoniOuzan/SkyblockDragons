package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

@Getter
public abstract class MiningMaterial extends ToolMaterial implements ItemStatsAble, ItemRequirementAble {
    private final Stats stats;
    private final int breakingPower;

    public MiningMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, int breakingPower, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, description, modifiers);
        this.stats = stats;
        this.breakingPower = breakingPower;
    }

    public MiningMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, int breakingPower, String description, MaterialModifier... modifiers) {
        this(itemID, material, family, name, type, rarity, new Stats(), breakingPower, description, modifiers);
    }
}
