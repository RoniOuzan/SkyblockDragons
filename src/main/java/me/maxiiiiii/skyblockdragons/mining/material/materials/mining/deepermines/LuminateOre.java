package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.deepermines;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.DeeperMinesOreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class LuminateOre extends DeeperMinesOreMaterial {
    public LuminateOre() {
        super("LUMINATE_ORE",
                new BlockMaterials(Material.QUARTZ_BLOCK),
                900,
                6,
                50,
                5,
                new BlockItemDrop(Items.get("LUMINATE"), 1)
        );
    }
}
