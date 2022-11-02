package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.hoes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class GoldHoe extends MiningMaterial {
    public GoldHoe() {
        super("GOLD_HOE",
                Material.GOLD_HOE,
                ItemFamily.GOLD,
                "Gold Hoe",
                ItemType.HOE,
                Rarity.COMMON,
                new Stats(300, 0),
                1,
                ""
        );
    }
}
