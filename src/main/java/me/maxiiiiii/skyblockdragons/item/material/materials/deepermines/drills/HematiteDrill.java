package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class HematiteDrill extends DrillMaterial {
    public HematiteDrill() {
        super("HEMATITE_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.HEMATITE,
                "Hematite Drill",
                Rarity.LEGENDARY,
                new Stats(190, 35, 0, 0, 0, 0, 1320, 290),
                9,
                ""
        );
    }
}
