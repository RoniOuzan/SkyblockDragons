package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class FarmingMaterial extends ToolMaterial implements ItemStatsAble, ItemRequirementAble {
    private final Stats stats;
    private final int breakingPower;
    protected Map<Material, Integer> cropAdder = new HashMap<>();

    public FarmingMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, int breakingPower, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, description, modifiers);
        this.stats = stats;
        this.breakingPower = breakingPower;
    }

    public FarmingMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, int breakingPower, String description, MaterialModifier... modifiers) {
        this(itemID, material, family, name, type, rarity, new Stats(), breakingPower, description, modifiers);
    }
}
