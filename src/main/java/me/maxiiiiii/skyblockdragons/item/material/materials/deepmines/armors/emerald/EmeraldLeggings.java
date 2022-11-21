package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.emerald;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class EmeraldLeggings extends ArmorMaterial {
    public EmeraldLeggings() {
        super("EMERALD_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.EMERALD,
                "Emerald Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new SurvivorStats(35, 20, 0, 0, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(5, 240, 15);
    }
}
