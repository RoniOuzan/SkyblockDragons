package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.redstone;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class RedstoneHelmet extends ArmorMaterial {
    public RedstoneHelmet() {
        super("REDSTONE_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.REDSTONE,
                "Redstone Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(200, 20, 20);
    }
}
