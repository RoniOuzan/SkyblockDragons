package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.ores;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class EmeraldOre extends OreMaterial {
    public EmeraldOre() {
        super("EMERALD_ORE",
                new BlockMaterials(Material.EMERALD_ORE),
                25,
                3,
                1.5,
                3,
                new BlockItemDrop(Items.get("EMERALD"), 1)
        );
    }
}
