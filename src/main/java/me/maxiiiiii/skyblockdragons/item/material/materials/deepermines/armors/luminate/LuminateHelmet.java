package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.luminate;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Material;

public class LuminateHelmet extends ArmorMaterial {
    public LuminateHelmet() {
        super("LUMINATE_HELMET",
                Material.CHAINMAIL_HELMET,
                ItemFamily.LUMINATE,
                "Luminate Helmet",
                ItemType.HELMET,
                Rarity.RARE,
                new Stats(70, 60, 0, 20, 165, 40, 2),
                "",
                ItemFullSetBonus.LUMINATE_FULL_SET
        );
    }
}
