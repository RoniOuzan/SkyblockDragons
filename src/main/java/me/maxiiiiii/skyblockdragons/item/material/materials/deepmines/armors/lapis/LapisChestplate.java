package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.lapis;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class LapisChestplate extends ArmorMaterial {
    public LapisChestplate() {
        super("LAPIS_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.LAPIS,
                "Lapis Chestplate",
                ItemType.CHESTPLATE,
                Rarity.COMMON,
                new SurvivorStats(20, 12, 0, 0, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(55, 55, 215);
    }
}
