package me.maxiiiiii.skyblockdragons.worlds.mining;

import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.ItemStats;
import me.maxiiiiii.skyblockdragons.material.Items;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BlockMaterial {
    STONE(15, 1, 1, new ItemDrop(Items.get("COBBLESTONE"), 1)),
    COAL_ORE(25, 1, 4, new ItemDrop(Items.get("COAL"), 1)),
    IRON_ORE(25, 2, 5, new ItemDrop(Items.get("IRON_INGOT"), 1)),
    GOLD_ORE(25, 2, 5, new ItemDrop(Items.get("GOLD_INGOT"), 1)),
    LAPIS_ORE(25, 2, 3, new ItemDrop(Items.get("LAPIS"), 4, 6)),
    REDSTONE_ORE(25, 3, 1.5, new ItemDrop(Items.get("REDSTONE"), 2, 4)),
    EMERALD_ORE(25, 3, 1.5, new ItemDrop(Items.get("EMERALD"), 1, 3)),
    DIAMOND_ORE(30, 3, 12, new ItemDrop(Items.get("DIAMOND"), 1)),
    DIAMOND_BLOCK(50, 4, 16, new ItemDrop(Items.get("DIAMOND_BLOCK"), 1)),
    OBSIDIAN(500, 4, 20, new ItemDrop(Items.get("OBSIDIAN"), 1)),
    ;

    public Material material;
    public final int blockStrength;
    public final int breakingPower;
    public final double miningXp;
    public List<ItemDrop> drops;

    BlockMaterial(Material material, int blockStrength, int breakingPower, double miningXp, ItemDrop... drops) {
        this.material = material;
        this.blockStrength = blockStrength;
        this.breakingPower = breakingPower;
        this.miningXp = miningXp;
        this.drops = Arrays.stream(drops).collect(Collectors.toList());
    }

    BlockMaterial(int blockStrength, int breakingPower, double miningXp, ItemDrop... drops) {
        this(null, blockStrength, breakingPower, miningXp, drops);
        this.material = Material.getMaterial(this.name());
    }

    public static BlockMaterial get(String name) {
        if (name.contains("GLOWING_REDSTONE"))
            name = name.replace("GLOWING_", "");
        return BlockMaterial.valueOf(name);
    }
}
