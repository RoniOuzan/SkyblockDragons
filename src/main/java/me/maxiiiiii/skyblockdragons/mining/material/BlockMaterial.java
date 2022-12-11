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
