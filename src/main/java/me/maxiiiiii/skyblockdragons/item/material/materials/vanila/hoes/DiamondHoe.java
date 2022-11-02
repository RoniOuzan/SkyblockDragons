package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.hoes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class DiamondHoe extends MiningMaterial {
    public DiamondHoe() {
        super("DIAMOND_HOE",
                Material.DIAMOND_HOE,
                ItemFamily.DIAMOND,
                "Diamond Hoe",
                ItemType.HOE,
                Rarity.UNCOMMON,
                new Stats(230, 0),
                1,
                ""
        );
    }
}
