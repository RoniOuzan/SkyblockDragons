package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.diamond;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class DiamondHelmet extends ArmorMaterial {
    public DiamondHelmet() {
        super("DIAMOND_HELMET",
                Material.DIAMOND_HELMET,
                ItemFamily.DIAMOND,
                "Diamond Helmet",
                ItemType.HELMET,
                Rarity.UNCOMMON,
                new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0),
                ""
        );
    }
}
