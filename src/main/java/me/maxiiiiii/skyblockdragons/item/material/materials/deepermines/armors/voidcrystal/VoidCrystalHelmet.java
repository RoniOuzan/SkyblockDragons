package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.voidcrystal;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.DeeperMinesFullSet;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class VoidCrystalHelmet extends ArmorMaterial {
    public VoidCrystalHelmet() {
        super("VOID_CRYSTAL_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.VOID_CRYSTAL,
                "Void Crystal Helmet",
                ItemType.HELMET,
                Rarity.MYTHIC,
                new Stats(135, 125, 70, 30, 275, 95, 20),
                "",
                new DeeperMinesFullSet(50)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(160, 20, 210);
    }
}
