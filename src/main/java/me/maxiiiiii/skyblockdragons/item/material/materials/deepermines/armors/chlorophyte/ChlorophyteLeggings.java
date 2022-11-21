package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.chlorophyte;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Color;
import org.bukkit.Material;

public class ChlorophyteLeggings extends ArmorMaterial {
    public ChlorophyteLeggings() {
        super("CHLOROPHYTE_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.CHLOROPHYTE,
                "Chlorophyte Leggings",
                ItemType.LEGGINGS,
                Rarity.UNCOMMON,
                new Stats(60, 40, 0, 20, 120, 30, 0),
                "",
                ItemFullSetBonus.CHLOROPHYTE_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(23, 228, 0);
    }
}
