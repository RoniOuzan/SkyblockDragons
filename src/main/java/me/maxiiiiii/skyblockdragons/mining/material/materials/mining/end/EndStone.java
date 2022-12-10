package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.end;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.material.types.MiningBlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class EndStone extends MiningBlockMaterial implements RespawnBlock {
    public EndStone() {
        super("END_STONE",
                new BlockMaterials(Material.ENDER_STONE, Material.SANDSTONE),
                200,
                3,
                30,
                0,
                new BlockItemDrop(Items.get("ENDER_STONE"), 1),
                new BlockItemRareDrop(Items.get("ENCHANTED_ENDER_STONE"), 1, 0.5)
        );
    }
}
