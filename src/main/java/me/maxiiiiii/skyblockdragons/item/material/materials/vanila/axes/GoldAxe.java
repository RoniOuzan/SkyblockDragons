package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.axes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class GoldAxe extends MiningMaterial {
    public GoldAxe() {
        super("GOLD_AXE",
                Material.GOLD_AXE,
                ItemFamily.GOLD,
                "Gold Axe",
                ItemType.AXE,
                Rarity.COMMON,
                new Stats(210, 0),
                1,
                ""
        );
    }
}
