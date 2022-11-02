package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.axes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class IronAxe extends MiningMaterial {
    public IronAxe() {
        super("IRON_AXE",
                Material.IRON_AXE,
                ItemFamily.IRON,
                "Iron Axe",
                ItemType.AXE,
                Rarity.COMMON,
                new Stats(120, 0),
                1,
                ""
        );
    }
}
