package me.maxiiiiii.skyblockdragons.mining.material.materials.mining;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.material.types.MiningBlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import org.bukkit.Material;

public class Obsidian extends MiningBlockMaterial implements RespawnBlock {
    public Obsidian() {
        super("OBSIDIAN",
                new BlockMaterials(Material.OBSIDIAN),
                500,
                4,
                20,
                0,
                new BlockItemDrop(Items.get("OBSIDIAN"), 1)
        );
    }
}
