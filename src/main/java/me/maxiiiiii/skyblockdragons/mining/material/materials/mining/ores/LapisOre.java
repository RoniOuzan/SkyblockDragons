package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.ores;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class LapisOre extends OreMaterial {
    public LapisOre() {
        super("LAPIS_ORE",
                new BlockMaterials(Material.LAPIS_ORE),
                25,
                2,
                5,
                2,
                new BlockItemDrop(Items.get("LAPIS"), 1)
        );
    }
}
