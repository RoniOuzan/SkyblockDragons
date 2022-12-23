package me.maxiiiiii.skyblockdragons.mining.material.materials.mining;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.material.types.MiningBlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class DiamondBlock extends MiningBlockMaterial implements RespawnBlock {
    public DiamondBlock() {
        super("DIAMOND_BLOCK",
                new BlockMaterials(Material.DIAMOND_BLOCK),
                5,
                4,
                16,
                0,
                new BlockItemDrop(Items.get("DIAMOND_BLOCK"), 1)
        );
    }
}
