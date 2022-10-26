package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class CobaltDrill extends DrillMaterial {
    public CobaltDrill() {
        super("COBALT_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.COBALT,
                "Cobalt Drill",
                Rarity.COMMON,
                new Stats(70, 0, 0, 0, 0, 0, 420, 60),
                5,
                ""
        );
    }
}
