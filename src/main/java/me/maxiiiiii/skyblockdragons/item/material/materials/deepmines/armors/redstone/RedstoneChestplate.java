package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.redstone;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class RedstoneChestplate extends ArmorMaterial {
    public RedstoneChestplate() {
        super("REDSTONE_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.REDSTONE,
                "Redstone Chestplate",
                ItemType.CHESTPLATE,
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
