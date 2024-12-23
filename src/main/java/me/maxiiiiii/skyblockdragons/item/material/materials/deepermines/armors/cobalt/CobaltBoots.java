package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.cobalt;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Material;

public class CobaltBoots extends ArmorMaterial {
    public CobaltBoots() {
        super("COBALT_BOOTS",
                Material.DIAMOND_BOOTS,
                ItemFamily.COBALT,
                "Cobalt Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new SurvivorStats(35, 20, 0, 20, 70, 15, 0),
                "",
                ItemFullSetBonus.COBALT_FULL_SET
        );
    }
}
