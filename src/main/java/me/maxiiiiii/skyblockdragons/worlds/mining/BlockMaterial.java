package me.maxiiiiii.skyblockdragons.worlds.mining;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum BlockMaterial {
    STONE(15, 1, 1, 0, new BlockDrop(Items.get("COBBLESTONE"), 1)),
    COAL_ORE(25, 1, 4, 2, new BlockDrop(Items.get("COAL"), 1)),
    IRON_ORE(25, 2, 5, 2, new BlockDrop(Items.get("IRON_INGOT"), 1)),
    GOLD_ORE(25, 2, 5, 2, new BlockDrop(Items.get("GOLD_INGOT"), 1)),
    LAPIS_ORE(25, 2, 3, 6, new BlockDrop(Items.get("LAPIS"), 4, 6)),
    REDSTONE_ORE(25, 3, 1.5, 3, new BlockDrop(Items.get("REDSTONE"), 2, 4)),
    EMERALD_ORE(25, 3, 1.5, 3, new BlockDrop(Items.get("EMERALD"), 1, 3)),
    DIAMOND_ORE(30, 3, 12, 4, new BlockDrop(Items.get("DIAMOND"), 1)),
    DIAMOND_BLOCK(50, 4, 16, 0, new BlockDrop(Items.get("DIAMOND_BLOCK"), 1)),
    OBSIDIAN(500, 4, 20, 0, new BlockDrop(Items.get("OBSIDIAN"), 1)),
    END_STONE(Material.ENDER_STONE, 150, 3, 30, 0, new BlockDrop(Items.get("ENDER_STONE"), 1))
    ;

    public Material material;
    public final int blockStrength;
    public final int breakingPower;
    public final double miningXp;
    public final int xp;
    public List<BlockDrop> drops;

    BlockMaterial(Material material, int blockStrength, int breakingPower, double miningXp, int xp, BlockDrop... drops) {
        this.material = material;
        this.blockStrength = blockStrength;
        this.breakingPower = breakingPower;
        this.miningXp = miningXp;
        this.xp = xp;
        this.drops = Arrays.stream(drops).collect(Collectors.toList());
    }

    BlockMaterial(int blockStrength, int breakingPower, double miningXp, int xp, BlockDrop... drops) {
        this(null, blockStrength, breakingPower, miningXp, xp, drops);
        this.material = Material.getMaterial(this.name());
    }

    public static BlockMaterial get(Material material) {
        if (material == Material.GLOWING_REDSTONE_ORE) material = Material.REDSTONE_ORE;

        for (BlockMaterial block : BlockMaterial.values()) {
            if (block.material == material)
                return block;
        }
        return null;
    }
}
