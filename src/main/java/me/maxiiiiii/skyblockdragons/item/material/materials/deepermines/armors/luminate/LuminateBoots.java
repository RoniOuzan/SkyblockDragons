package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.luminate;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.Material;

public class LuminateBoots extends ArmorMaterial {
    public LuminateBoots() {
        super("LUMINATE_BOOTS",
                Material.IRON_BOOTS,
                ItemFamily.LUMINATE,
                "Luminate Boots",
                ItemType.BOOTS,
                Rarity.RARE,
                new SurvivorStats(65, 55, 0, 20, 150, 35, 2),
                "",
                ItemFullSetBonus.LUMINATE_FULL_SET
        );
    }
}
