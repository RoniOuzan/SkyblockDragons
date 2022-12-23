package me.maxiiiiii.skyblockdragons.mining.material.types;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;

public abstract class ForagingBlockMaterial extends BlockMaterial implements RespawnBlock {
    public ForagingBlockMaterial(String itemID, BlockMaterials material, double blockStrength, int breakingPower, double skillExp, int experience, BlockDrop... drops) {
        super(itemID, material, blockStrength, breakingPower, Arrays.asList(ItemType.AXE, ItemType.NULL), skillExp, SkillType.FORAGING, experience, drops);
    }

    @Override
    public Entry<Material, Integer> getBlockWhenBreaks(PlayerSD player, Block block) {
        return new Entry<>(Material.AIR, 0);
    }

    @Override
    public long getTimeToRespawn(PlayerSD player, Block block) {
        return 200;
    }
}
