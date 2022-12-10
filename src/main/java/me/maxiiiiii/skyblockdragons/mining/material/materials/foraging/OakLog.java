package me.maxiiiiii.skyblockdragons.mining.material.materials.foraging;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.ForagingBlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class OakLog extends ForagingBlockMaterial {
    public OakLog() {
        super("OAK_LOG",
                new BlockMaterials(Material.LOG, 0, Material.LOG, 12),
                25,
                1,
                1,
                0,
                new BlockItemDrop(Items.get("LOG"), 1)
        );
    }
}
