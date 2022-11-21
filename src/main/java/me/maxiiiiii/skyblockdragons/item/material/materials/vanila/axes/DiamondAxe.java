package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.axes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class DiamondAxe extends MiningMaterial {
    public DiamondAxe() {
        super("DIAMOND_AXE",
                Material.DIAMOND_AXE,
                ItemFamily.DIAMOND,
                "Diamond Axe",
                ItemType.AXE,
                Rarity.UNCOMMON,
                new Stats(230, 0),
                1,
                ""
        );
    }
}
