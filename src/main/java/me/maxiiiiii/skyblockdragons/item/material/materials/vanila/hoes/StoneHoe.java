package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.hoes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class StoneHoe extends MiningMaterial {
    public StoneHoe() {
        super("STONE_HOE",
                Material.STONE_HOE,
                ItemFamily.STONE,
                "Stone Hoe",
                ItemType.HOE,
                Rarity.COMMON,
                new Stats(80, 0),
                1,
                ""
        );
    }
}
