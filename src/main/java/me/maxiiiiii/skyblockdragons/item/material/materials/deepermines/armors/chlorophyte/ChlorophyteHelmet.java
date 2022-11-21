package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.chlorophyte;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class ChlorophyteHelmet extends ArmorMaterial {
    public ChlorophyteHelmet() {
        super("CHLOROPHYTE_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.CHLOROPHYTE,
                "Chlorophyte Helmet",
                ItemType.HELMET,
                Rarity.UNCOMMON,
                new SurvivorStats(50, 35, 0, 20, 115, 25, 0),
                "",
                ItemFullSetBonus.CHLOROPHYTE_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(27, 190, 0);
    }
}
