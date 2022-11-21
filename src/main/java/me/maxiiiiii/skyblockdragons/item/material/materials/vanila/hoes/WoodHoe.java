package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.hoes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class WoodHoe extends MiningMaterial {
    public WoodHoe() {
        super("WOOD_HOE",
                Material.WOOD_HOE,
                ItemFamily.WOOD,
                "Wood Hoe",
                ItemType.HOE,
                Rarity.COMMON,
                new Stats(50, 0),
                1,
                ""
        );
    }
}
