package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.diamond;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class DiamondLeggings extends ArmorMaterial {
    public DiamondLeggings() {
        super("DIAMOND_LEGGINGS",
                Material.DIAMOND_LEGGINGS,
                ItemFamily.DIAMOND,
                "Diamond Leggings",
                ItemType.LEGGINGS,
                Rarity.UNCOMMON,
                new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0),
                ""
        );
    }
}
