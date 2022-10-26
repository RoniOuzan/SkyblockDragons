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

public class CobaltChestplate extends ArmorMaterial {
    public CobaltChestplate() {
        super("COBALT_CHESTPLATE",
                Material.DIAMOND_CHESTPLATE,
                ItemFamily.COBALT,
                "Cobalt Chestplate",
                ItemType.CHESTPLATE,
                Rarity.COMMON,
                new Stats(50, 35, 0, 20, 90, 25, 0),
                "",
                new DeeperMinesFullSet(5)
        );
    }
}
