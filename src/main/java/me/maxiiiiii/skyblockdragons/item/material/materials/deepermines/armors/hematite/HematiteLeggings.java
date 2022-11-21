package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.hematite;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class HematiteLeggings extends ArmorMaterial {
    public HematiteLeggings() {
        super("HEMATITE_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.HEMATITE,
                "Hematite Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new SurvivorStats(125, 100, 60, 30, 290, 105, 10),
                "",
                ItemFullSetBonus.HEMATITE_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(70, 0, 0);
    }
}
