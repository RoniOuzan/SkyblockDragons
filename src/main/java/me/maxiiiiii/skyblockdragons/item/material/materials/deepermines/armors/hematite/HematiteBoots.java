package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.hematite;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Color;
import org.bukkit.Material;

public class HematiteBoots extends ArmorMaterial {
    public HematiteBoots() {
        super("HEMATITE_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.HEMATITE  ,
                "Hematite Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(105, 85, 40, 30, 260, 90, 10),
                "",
                ItemFullSetBonus.HEMATITE_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(40, 0, 0);
    }
}
