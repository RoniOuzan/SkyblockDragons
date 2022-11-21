package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.voidcrystal;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Color;
import org.bukkit.Material;

public class VoidCrystalChestplate extends ArmorMaterial {
    public VoidCrystalChestplate() {
        super("VOID_CRYSTAL_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.VOID_CRYSTAL,
                "Void Crystal Chestplate",
                ItemType.CHESTPLATE,
                Rarity.MYTHIC,
                new Stats(145, 140, 120, 30, 310, 110, 20),
                "",
                ItemFullSetBonus.VOID_CRYSTAL_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(180, 40, 225);
    }
}
