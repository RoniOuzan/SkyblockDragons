package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class ChlorophyteDrill extends DrillMaterial {
    public ChlorophyteDrill() {
        super("CHLOROPHYTE_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.CHLOROPHYTE,
                "Chlorophyte Drill",
                Rarity.UNCOMMON,
                new Stats(90, 0, 0, 0, 0, 0, 600, 110),
                6,
                ""
        );
    }
}
