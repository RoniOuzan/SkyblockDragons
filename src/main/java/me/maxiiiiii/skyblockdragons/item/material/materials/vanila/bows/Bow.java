package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.bows;

import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class Bow extends BowMaterial {
    public Bow() {
        super("BOW",
                Material.BOW,
                ItemFamily.WOOD,
                "Bow",
                Rarity.COMMON,
                new Stats(5, 5, 0, 0, 0, 0, 0, 0, 0, 0),
                ""
        );
    }
}
