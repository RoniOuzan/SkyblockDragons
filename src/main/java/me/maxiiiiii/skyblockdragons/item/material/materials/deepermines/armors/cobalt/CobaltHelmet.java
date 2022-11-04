package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.cobalt;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Material;

public class CobaltHelmet extends ArmorMaterial {
    public CobaltHelmet() {
        super("COBALT_HELMET",
                Material.DIAMOND_HELMET,
                ItemFamily.COBALT,
                "Cobalt Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(35, 25, 0, 20, 75, 20, 0),
                "",
                ItemFullSetBonus.COBALT_FULL_SET
        );
    }
}
