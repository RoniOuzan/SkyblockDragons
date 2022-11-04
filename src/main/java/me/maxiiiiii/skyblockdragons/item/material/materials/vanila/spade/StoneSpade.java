package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.spade;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class StoneSpade extends MiningMaterial {
    public StoneSpade() {
        super("STONE_SPADE",
                Material.STONE_SPADE,
                ItemFamily.STONE,
                "Stone Spade",
                ItemType.SPADE,
                Rarity.COMMON,
                new Stats(80, 0),
                1,
                ""
        );
    }
}
