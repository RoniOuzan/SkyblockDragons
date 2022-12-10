package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.deepermines;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.DeeperMinesOreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class DernicOre extends DeeperMinesOreMaterial {
    public DernicOre() {
        super("DERNIC_ORE",
                new BlockMaterials(Material.STAINED_CLAY, 7),
                1300,
                6,
                80,
                7,
                new BlockItemDrop(Items.get("DERNIC"), 1)
        );
    }
}
