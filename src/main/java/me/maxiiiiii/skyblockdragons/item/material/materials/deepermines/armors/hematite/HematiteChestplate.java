package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.hematite;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class HematiteChestplate extends ArmorMaterial {
    public HematiteChestplate() {
        super("HEMATITE_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.HEMATITE,
                "Hematite Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new SurvivorStats(130, 105, 80, 30, 310, 110, 10),
                "",
                ItemFullSetBonus.HEMATITE_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(100, 0, 0);
    }
}
