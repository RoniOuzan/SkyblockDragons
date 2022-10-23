package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.cobalt;

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

public class CobaltBoots extends ArmorMaterial {
    public CobaltBoots() {
        super("COBALT_BOOTS",
                Material.DIAMOND_HELMET,
                ItemFamily.COBALT,
                "Cobalt Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(35, 25, 0, 20, 75, 20, 0),
                "",
                new DeeperMinesFullSet(5)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(136,131,126);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
