package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.gold;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class GoldHelmet extends ArmorMaterial {
    public GoldHelmet() {
        super("GOLD_HELMET",
                Material.GOLD_HELMET,
                ItemFamily.GOLD,
                "Gold Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0),
                ""
        );
    }
}
