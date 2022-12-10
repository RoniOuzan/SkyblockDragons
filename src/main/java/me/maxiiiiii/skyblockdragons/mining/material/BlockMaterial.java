package me.maxiiiiii.skyblockdragons.mining.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.item.material.types.MaterialUnregisters;
import me.maxiiiiii.skyblockdragons.item.material.types.QuickMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.VanillaMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Material;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/*
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
    VOID_CRYSTAL_ORE(Material.SEA_LANTERN, 2500, 9, 180, 15, new BlockItemDrop(Items.get("VOID_CRYSTAL"), 1)),
 */

@Getter
public abstract class BlockMaterial {
    public static final Map<String, BlockMaterial> blocks = new HashMap<>();

    private final String itemID;
    private final BlockMaterials materials;
    private final int blockStrength;
    private final int breakingPower;
    private final List<ItemType> requiredTools;
    private final double skillExp;
    private final SkillType skill;
    private final int experience;
    private final List<BlockDrop> drops;

    public BlockMaterial(String itemID, BlockMaterials material, int blockStrength, int breakingPower, List<ItemType> requiredTools, double skillExp, SkillType skill, int experience, BlockDrop... drops) {
        this.itemID = itemID;
        this.materials = material;
        this.blockStrength = blockStrength;
        this.breakingPower = breakingPower;
        this.requiredTools = requiredTools;
        this.skillExp = skillExp;
        this.skill = skill;
        this.experience = experience;
        this.drops = Arrays.asList(drops);
    }

    public BlockMaterial(String itemID, BlockMaterials material, int blockStrength, int breakingPower, ItemType requiredTools, double skillExp, SkillType skill, int experience, BlockDrop... drops) {
        this(itemID, material, blockStrength, breakingPower, Collections.singletonList(requiredTools), skillExp, skill, experience, drops);
    }

    public List<WorldSD> getWorlds() {
        return WorldSD.worlds;
    }

    public static void registerBlocks() {
        Set<Class<? extends BlockMaterial>> materials = new Reflections("me.maxiiiiii").getSubTypesOf(BlockMaterial.class).stream().filter(c -> !Modifier.isAbstract(c.getModifiers())).collect(Collectors.toSet());
        for (Class<? extends BlockMaterial> materialClass : materials) {
            if (materialClass.isAnnotationPresent(MaterialUnregisters.class) ||
                    QuickMaterial.class.isAssignableFrom(materialClass) ||
                    VanillaMaterial.class.isAssignableFrom(materialClass)
            ) continue;

            try {
                BlockMaterial material = materialClass.newInstance();

                blocks.put(material.getItemID(), material);
            } catch (InstantiationException | IllegalAccessException e) {
                SkyblockDragons.logger.severe("Failed to register material " + e.getMessage());
            }
        }
    }

    public String name() {
        return this.getItemID();
    }

    public static BlockMaterial get(WorldSD world, Material material, byte data) {
        if (material == Material.GLOWING_REDSTONE_ORE) material = Material.REDSTONE_ORE;

        for (BlockMaterial block : BlockMaterial.blocks.values()) {
            if (block.materials.contains(material, data) && block.getWorlds().contains(world))
                return block;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
