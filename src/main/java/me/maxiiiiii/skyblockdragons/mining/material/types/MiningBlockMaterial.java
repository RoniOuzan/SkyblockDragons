package me.maxiiiiii.skyblockdragons.mining.material.types;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;

import java.util.Arrays;

public abstract class MiningBlockMaterial extends BlockMaterial {
    public MiningBlockMaterial(String itemID, BlockMaterials material, int blockStrength, int breakingPower, double skillExp, int experience, BlockDrop... drops) {
        super(itemID, material, blockStrength, breakingPower, Arrays.asList(ItemType.PICKAXE, ItemType.DRILL), skillExp, SkillType.MINING, experience, drops);
    }
}
