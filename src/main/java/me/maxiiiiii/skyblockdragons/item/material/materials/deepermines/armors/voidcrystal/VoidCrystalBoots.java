package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.voidcrystal;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Color;
import org.bukkit.Material;

public class VoidCrystalBoots extends ArmorMaterial {
    public VoidCrystalBoots() {
        super("VOID_CRYSTAL_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.VOID_CRYSTAL,
                "Void Crystal Boots",
                ItemType.BOOTS,
                Rarity.MYTHIC,
                new Stats(130, 120, 60, 30, 260, 90, 20),
                "",
                ItemFullSetBonus.VOID_CRYSTAL_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(200, 70, 235);
    }
}
