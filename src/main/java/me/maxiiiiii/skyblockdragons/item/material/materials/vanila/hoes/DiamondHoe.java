package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.hoes;

import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class DiamondHoe extends FarmingMaterial {
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
