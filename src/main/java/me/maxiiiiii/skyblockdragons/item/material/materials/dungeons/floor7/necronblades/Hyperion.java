package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.necronblades;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class Hyperion extends NecronBladeMaterial {
    public Hyperion() {
        super("HYPERION",
                Material.IRON_SWORD,
                ItemFamily.NECRON_BLADE,
                "Hyperion",
                Rarity.LEGENDARY,
                new Stats(260, 150, 0, 0, 0, 30, 0, 0, 0, 350),
                NecronBladeMaterial.NecronBladeType.HYPERION
        );
    }
}
