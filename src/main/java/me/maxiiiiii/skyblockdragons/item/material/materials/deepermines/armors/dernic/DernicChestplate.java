package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.dernic;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class DernicChestplate extends ArmorMaterial {
    public DernicChestplate() {
        super("DERNIC_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.LUMINATE,
                "Dernic Chestplate",
                ItemType.CHESTPLATE,
                Rarity.EPIC,
                new SurvivorStats(100, 105, 0, 30, 205, 80, 5),
                "",
                ItemFullSetBonus.DERNIC_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(190, 100, 35);
    }
}
