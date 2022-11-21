package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.axes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class StoneAxe extends MiningMaterial {
    public StoneAxe() {
        super("STONE_AXE",
                Material.STONE_AXE,
                ItemFamily.STONE,
                "Stone Axe",
                ItemType.AXE,
                Rarity.COMMON,
                new Stats(80, 5),
                1,
                ""
        );
    }
}
