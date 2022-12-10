package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.deepermines;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.types.DeeperMinesOreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class VoidCrystalOre extends DeeperMinesOreMaterial {
    public VoidCrystalOre() {
        super("VOID_CRYSTAL_ORE",
                new BlockMaterials(Material.SEA_LANTERN),
                2500,
                8,
                120,
                8,
                new BlockItemDrop(Items.get("VOID_CRYSTAL"), 1)
        );
    }

    @Override
    public Entry<Material, Integer> getRespawnsTo(PlayerSD player, Block block, Material defaultMaterial, int defaultData) {
        return new Entry<>(Material.NETHER_BRICK, 0);
    }
}
