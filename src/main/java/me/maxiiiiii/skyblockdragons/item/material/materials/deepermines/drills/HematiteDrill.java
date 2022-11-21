package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class HematiteDrill extends DrillMaterial {
    public HematiteDrill() {
        super("HEMATITE_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.HEMATITE,
                "Hematite Drill",
                Rarity.LEGENDARY,
                new DamageStats(190, 35, 0, 0, 0, 0, 1320, 290),
                9,
                ""
        );
    }
}
