package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.spade;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class GoldSpade extends MiningMaterial {
    public GoldSpade() {
        super("GOLD_SPADE",
                Material.GOLD_SPADE,
                ItemFamily.GOLD,
                "Gold Spade",
                ItemType.SPADE,
                Rarity.COMMON,
                new Stats(300, 0),
                1,
                ""
        );
    }
}
