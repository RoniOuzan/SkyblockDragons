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

public class HematiteLeggings extends ArmorMaterial {
    public HematiteLeggings() {
        super("HEMATITE_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.HEMATITE,
                "Hematite Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new Stats(125, 100, 60, 30, 290, 105, 10),
                "",
                new DeeperMinesFullSet(30)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(70, 0, 0);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
