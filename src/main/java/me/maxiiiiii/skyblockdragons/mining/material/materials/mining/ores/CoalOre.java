package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.ores;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class CoalOre extends OreMaterial {
    public CoalOre() {
        super("COAL_ORE",
                new BlockMaterials(Material.COAL_ORE),
                25,
                1,
                4,
                2,
                new BlockItemDrop(Items.get("COAL"), 1)
        );
    }
}
