package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.hematite;

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

public class HematiteHelmet extends ArmorMaterial {
    public HematiteHelmet() {
        super("HEMATITE_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.HEMATITE,
                "Hematite Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(115, 90, 50, 30, 275, 95, 10),
                "",
                new DeeperMinesFullSet(30)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(120, 0, 0);
    }
}
