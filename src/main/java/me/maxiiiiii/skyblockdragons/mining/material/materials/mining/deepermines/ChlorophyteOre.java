package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.deepermines;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.DeeperMinesOreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class ChlorophyteOre extends DeeperMinesOreMaterial {
    public ChlorophyteOre() {
        super("CHLOROPHYTE_ORE",
                new BlockMaterials(Material.CONCRETE, 5),
                600,
                5,
                35,
                4,
                new BlockItemDrop(Items.get("CHLOROPHYTE"), 1)
        );
    }
}
