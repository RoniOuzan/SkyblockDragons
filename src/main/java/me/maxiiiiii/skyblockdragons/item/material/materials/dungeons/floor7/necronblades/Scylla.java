package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.necronblades;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class Scylla extends NecronBladeMaterial {
    public Scylla() {
        super("SCYLLA",
                Material.IRON_SWORD,
                ItemFamily.NECRON_BLADE,
                "Scylla",
                Rarity.LEGENDARY,
                new Stats(270, 150, 35, 12, 0, 30, 0, 0, 0, 50),
                NecronBladeMaterial.NecronBladeType.SCYLLA
        );
    }
}
