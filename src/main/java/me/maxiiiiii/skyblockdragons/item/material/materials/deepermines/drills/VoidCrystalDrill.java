package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class VoidCrystalDrill extends DrillMaterial {
    public VoidCrystalDrill() {
        super("VOID_CRYSTAL_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.VOID_CRYSTAL,
                "Void Crystal Drill",
                Rarity.MYTHIC,
                new DamageStats(265, 60, 0, 0, 0, 0, 1610, 410),
                10,
                ""
        );
    }
}
