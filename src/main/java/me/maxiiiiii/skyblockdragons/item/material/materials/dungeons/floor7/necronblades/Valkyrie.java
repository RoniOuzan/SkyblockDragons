package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.necronblades;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class Valkyrie extends NecronBladeMaterial {
    public Valkyrie() {
        super("VALKYRIE",
                Material.IRON_SWORD,
                ItemFamily.NECRON_BLADE,
                "Valkyrie",
                Rarity.LEGENDARY,
                new Stats(270, 145, 0, 0, 0, 60, 0, 0, 0, 60),
                NecronBladeMaterial.NecronBladeType.VALKYRIE
        );
    }
}
