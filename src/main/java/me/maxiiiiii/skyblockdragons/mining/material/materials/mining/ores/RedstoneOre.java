package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.ores;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class RedstoneOre extends OreMaterial {
    public RedstoneOre() {
        super("REDSTONE_ORE",
                new BlockMaterials(Material.REDSTONE_ORE),
                3,
                2,
                3,
                6,
                new BlockItemDrop(Items.get("REDSTONE"), 1, 2, 100)
        );
    }
}
