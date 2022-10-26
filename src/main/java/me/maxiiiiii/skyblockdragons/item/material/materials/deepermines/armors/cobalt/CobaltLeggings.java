package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.cobalt;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.DeeperMinesFullSet;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class CobaltLeggings extends ArmorMaterial {
    public CobaltLeggings() {
        super("COBALT_LEGGINGS",
                Material.DIAMOND_LEGGINGS,
                ItemFamily.COBALT,
                "Cobalt Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new Stats(40, 30, 0, 20, 80, 20, 0),
                "",
                new DeeperMinesFullSet(5)
        );
    }
}
