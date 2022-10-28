package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.drills;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class VoidCrystalDrill extends DrillMaterial {
    public VoidCrystalDrill() {
        super("VOID_CRYSTAL_DRILL",
                Material.PRISMARINE_SHARD,
                ItemFamily.VOID_CRYSTAL,
                "Void Crystal Drill",
                Rarity.MYTHIC,
                new Stats(265, 60, 0, 0, 0, 0, 1610, 410),
                10,
                ""
        );
    }
}