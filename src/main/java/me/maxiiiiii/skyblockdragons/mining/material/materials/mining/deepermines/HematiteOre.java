package me.maxiiiiii.skyblockdragons.mining.material.materials.mining.deepermines;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.events.UpdateVoidCrystalChanceEvent;
import me.maxiiiiii.skyblockdragons.mining.material.types.DeeperMinesOreMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class HematiteOre extends DeeperMinesOreMaterial {
    public HematiteOre() {
        super("HEMATITE_ORE",
                new BlockMaterials(Material.NETHER_BRICK),
                1600,
                8,
                120,
                8,
                new BlockItemDrop(Items.get("HEMATITE"), 1)
        );
    }

    @Override
    public Entry<Material, Integer> getBlockWhenBreaks(PlayerSD player, Block block) {
        UpdateVoidCrystalChanceEvent event = new UpdateVoidCrystalChanceEvent(player, 0.5, block, this);
        Bukkit.getPluginManager().callEvent(event);

        if (Math.random() <= event.getMultiplier().multiply(event.getBaseChance())) {
            return new Entry<>(Material.SEA_LANTERN, 0);
        }

        return new Entry<>(Material.BEDROCK, 0);
    }
}
