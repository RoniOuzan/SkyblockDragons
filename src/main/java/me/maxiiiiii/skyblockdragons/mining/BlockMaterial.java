package me.maxiiiiii.skyblockdragons.mining;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

@Getter
public enum BlockMaterial {
    STONE(15, 1, 1, 0, new BlockItemDrop(Items.get("COBBLESTONE"), 1)),
    COAL_ORE(25, 1, 4, 2, new BlockItemDrop(Items.get("COAL"), 1)),
    IRON_ORE(25, 2, 5, 2, new BlockItemDrop(Items.get("IRON_INGOT"), 1)),
    GOLD_ORE(25, 2, 5, 2, new BlockItemDrop(Items.get("GOLD_INGOT"), 1)),
    LAPIS_ORE(25, 2, 3, 6, new BlockItemDrop(Items.get("LAPIS"), 4)),
    REDSTONE_ORE(25, 3, 1.5, 3, new BlockItemDrop(Items.get("REDSTONE"), 2)),
    EMERALD_ORE(25, 3, 1.5, 3, new BlockItemDrop(Items.get("EMERALD"), 1)),
    DIAMOND_ORE(30, 3, 12, 4, new BlockItemDrop(Items.get("DIAMOND"), 1)),
    DIAMOND_BLOCK(50, 4, 16, 0, new BlockItemDrop(Items.get("DIAMOND_BLOCK"), 1)),
    OBSIDIAN(500, 4, 20, 0, new BlockItemDrop(Items.get("OBSIDIAN"), 1)),

    END_STONE(Material.ENDER_STONE, 200, 3, 30, 0, new BlockItemDrop(Items.get("ENDER_STONE"), 1), new BlockItemRareDrop(Items.get("ENCHANTED_ENDER_STONE"), 1, 0.5)),
    SAND_STONE(Material.SANDSTONE, 200, 3, 30, 0, new BlockItemDrop(Items.get("ENDER_STONE"), 1), new BlockItemRareDrop(Items.get("ENCHANTED_ENDER_STONE"), 1, 0.5)),

    COBALT_ORE(Material.PRISMARINE, 400, 4, 25, 2, new BlockItemDrop(Items.get("COBALT"), 1)),
    CHLOROPHYTE_ORE(Material.CONCRETE, 5, 600, 5, 35, 4, Material.BEDROCK, new BlockItemDrop(Items.get("CHLOROPHYTE"), 1)),
    LUMINATE_ORE(Material.QUARTZ_BLOCK, 900, 6, 50, 5, new BlockItemDrop(Items.get("LUMINATE"), 1)),
    DERNIC_ORE(Material.STAINED_CLAY, 7, 1300, 6, 80, 7, Material.BEDROCK, new BlockItemDrop(Items.get("DERNIC"), 1)),
    HEMATITE_ORE(Material.NETHER_BRICK, 1600, 8, 120, 9, new BlockItemDrop(Items.get("HEMATITE"), 1)),
    VOID_CRYSTAL_ORE(Material.SEA_LANTERN, 2500, 9, 180, 15, new BlockItemDrop(Items.get("VOID_CRYSTAL"), 1))
    ;

    public Material material;
    public int materialData;
    public final int blockStrength;
    public final int breakingPower;
    public final double miningXp;
    public final int xp;
    public List<BlockDrop> drops;
    private final Material breaksTo;

    BlockMaterial(Material material, int materialData, int blockStrength, int breakingPower, double miningXp, int xp, Material breaksTo, BlockDrop... drops) {
        this.material = material;
        this.materialData = materialData;
        this.blockStrength = blockStrength;
        this.breakingPower = breakingPower;
        this.miningXp = miningXp;
        this.xp = xp;
        this.breaksTo = breaksTo;
        this.drops = Arrays.asList(drops);
    }

    BlockMaterial(Material material, int blockStrength, int breakingPower, double miningXp, int xp, BlockDrop... drops) {
        this(material, 1, blockStrength, breakingPower, miningXp, xp, Material.BEDROCK, drops);
    }

    BlockMaterial(int blockStrength, int breakingPower, double miningXp, int xp, BlockDrop... drops) {
        this(null, 0, blockStrength, breakingPower, miningXp, xp, Material.BEDROCK, drops);
        this.material = Material.getMaterial(this.name());
    }

    public static boolean isMiningFortuneAble(Block block) {
        BlockMaterial material = BlockMaterial.get(block.getType());
        if (material != null)
            return material.name().contains("ORE");
        return false;
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
