package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.chlorophyte;

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

public class ChlorophyteHelmet extends ArmorMaterial {
    public ChlorophyteHelmet() {
        super("CHLOROPHYTE_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.CHLOROPHYTE,
                "Chlorophyte Helmet",
                ItemType.HELMET,
                Rarity.UNCOMMON,
                new Stats(50, 35, 0, 20, 115, 25, 0),
                "",
                new DeeperMinesFullSet(10)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(27, 190, 0);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
