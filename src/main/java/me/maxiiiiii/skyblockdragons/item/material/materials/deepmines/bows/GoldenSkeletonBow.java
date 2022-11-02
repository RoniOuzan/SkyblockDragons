package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.bows;

import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class GoldenSkeletonBow extends BowMaterial {
    public GoldenSkeletonBow() {
        super("GOLDEN_SKELETON_BOW",
                Material.BOW,
                ItemFamily.NULL,
                "Golden Skeleton Bow",
                Rarity.COMMON,
                new Stats(30, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                ""
        );
    }
}
