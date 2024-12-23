package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class DernicDrill extends DrillMaterial {
    public DernicDrill() {
        super("DERNIC_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.DERNIC,
                "Dernic Drill",
                Rarity.EPIC,
                new DamageStats(155, 35, 0, 0, 0, 0, 910, 210),
                8,
                ""
        );
    }
}
