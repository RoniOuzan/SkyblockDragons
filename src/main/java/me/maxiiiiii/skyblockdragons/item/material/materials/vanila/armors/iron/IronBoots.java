package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronBoots extends ArmorMaterial {
    public IronBoots() {
        super("IRON_BOOTS",
                Material.IRON_BOOTS,
                ItemFamily.IRON,
                "Iron Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0),
                ""
        );
    }
}