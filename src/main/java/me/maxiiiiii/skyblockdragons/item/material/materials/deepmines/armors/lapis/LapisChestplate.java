package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.lapis;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

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
                new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(55, 55, 215);
    }
}
