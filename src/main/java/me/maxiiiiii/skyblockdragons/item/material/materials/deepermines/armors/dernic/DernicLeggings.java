package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.dernic;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Color;
import org.bukkit.Material;

public class DernicLeggings extends ArmorMaterial {
    public DernicLeggings() {
        super("DERNIC_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.LUMINATE,
                "Dernic Leggings",
                ItemType.LEGGINGS,
                Rarity.EPIC,
                new Stats(90, 100, 0, 30, 200, 75, 5),
                "",
                ItemFullSetBonus.DERNIC_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(140, 85, 25);
    }
}
