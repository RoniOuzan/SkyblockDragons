package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class MiningMaterial extends ToolMaterial implements ItemStatsAble, ItemRequirementAble {
    private final Stats stats;
    private final int breakingPower;

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, int breakingPower, String description, Object... objects) {
        super(material, family, name, type, rarity, id, nbt, 0, description, objects);
        this.stats = stats;
        this.breakingPower = breakingPower;
    }

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, int breakingPower, String description, Object... objects) {
        this(material, family, name, type, rarity, "", "", stats, breakingPower, description, objects);
    }

    public MiningMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, int breakingPower, String description, Object... objects) {
        this(material, family, name, type, rarity, "", "", new Stats(), breakingPower, description, objects);
    }
}
