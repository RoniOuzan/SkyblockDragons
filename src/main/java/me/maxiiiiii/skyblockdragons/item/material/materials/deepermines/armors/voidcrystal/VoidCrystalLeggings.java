package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.voidcrystal;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class VoidCrystalLeggings extends ArmorMaterial {
    public VoidCrystalLeggings() {
        super("VOID_CRYSTAL_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.VOID_CRYSTAL,
                "Void Crystal Leggings",
                ItemType.LEGGINGS,
                Rarity.MYTHIC,
                new SurvivorStats(140, 135, 80, 30, 290, 105, 20),
                "",
                ItemFullSetBonus.VOID_CRYSTAL_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(190, 60, 230);
    }
}
