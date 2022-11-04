package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.luminate;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.Material;

public class LuminateLeggings extends ArmorMaterial {
    public LuminateLeggings() {
        super("LUMINATE_LEGGINGS",
                Material.CHAINMAIL_LEGGINGS,
                ItemFamily.LUMINATE,
                "Luminate Leggings",
                ItemType.LEGGINGS,
                Rarity.RARE,
                new Stats(80, 65, 0, 20, 120, 45, 2),
                "",
                ItemFullSetBonus.LUMINATE_FULL_SET
        );
    }
}
