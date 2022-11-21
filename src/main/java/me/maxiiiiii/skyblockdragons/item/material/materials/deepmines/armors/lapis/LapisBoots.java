package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.lapis;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Color;
import org.bukkit.Material;

public class LapisBoots extends ArmorMaterial {
    public LapisBoots() {
        super("LAPIS_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.LAPIS,
                "Lapis Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(55, 55, 215);
    }
}
