package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.ores;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class DiamondOre extends OreMaterial {
    public DiamondOre() {
        super("DIAMOND_ORE",
                new BlockMaterials(Material.DIAMOND_ORE),
                30,
                3,
                12,
                4,
                new BlockItemDrop(Items.get("DIAMOND"), 1)
        );
    }
}
