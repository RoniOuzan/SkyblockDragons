package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.axes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class WoodAxe extends MiningMaterial {
    public WoodAxe() {
        super("WOOD_AXE",
                Material.WOOD_AXE,
                ItemFamily.WOOD,
                "Wood Axe",
                ItemType.AXE,
                Rarity.COMMON,
                new Stats(50, 0),
                1,
                ""
        );
    }
}
