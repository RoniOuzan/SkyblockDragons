package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.cobalt;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Material;

public class CobaltLeggings extends ArmorMaterial {
    public CobaltLeggings() {
        super("COBALT_LEGGINGS",
                Material.DIAMOND_LEGGINGS,
                ItemFamily.COBALT,
                "Cobalt Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new SurvivorStats(40, 30, 0, 20, 80, 20, 0),
                "",
                ItemFullSetBonus.COBALT_FULL_SET
        );
    }
}
