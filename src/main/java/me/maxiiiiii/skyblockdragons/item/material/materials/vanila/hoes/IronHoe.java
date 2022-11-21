package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.hoes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronHoe extends MiningMaterial {
    public IronHoe() {
        super("IRON_HOE",
                Material.IRON_HOE,
                ItemFamily.IRON,
                "Iron Hoe",
                ItemType.HOE,
                Rarity.COMMON,
                new Stats(120, 0),
                1,
                ""
        );
    }
}
