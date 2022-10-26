package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class LuminateDrill extends DrillMaterial {
    public LuminateDrill() {
        super("LUMINATE_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.LUMINATE,
                "Luminate Drill",
                Rarity.RARE,
                new Stats(120, 20, 0, 0, 0, 0, 730, 160),
                7,
                ""
        );
    }
}
