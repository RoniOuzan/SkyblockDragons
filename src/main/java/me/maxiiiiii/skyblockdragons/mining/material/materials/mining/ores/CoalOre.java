package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.ores;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CoalOre extends OreMaterial {
    public CoalOre() {
        super("COAL_ORE",
                new BlockMaterials(Material.COAL_ORE),
                3,
                1,
                4,
                2,
                new BlockItemDrop(Items.get("COAL"), 1)
        );
    }

    @Override
    public Entry<Material, Integer> getRespawnsTo(PlayerSD player, Block block, Material defaultMaterial, int defaultData) {
        if (player.getWorldSD() == WorldSD.HUB) {
            return new Entry<>(Material.STONE, 0);
        }
        return super.getRespawnsTo(player, block, defaultMaterial, defaultData);
    }
}
