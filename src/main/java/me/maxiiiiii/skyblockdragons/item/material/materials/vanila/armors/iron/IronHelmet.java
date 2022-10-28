package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import org.bukkit.Material;

public class IronHelmet extends ArmorMaterial {
    public IronHelmet() {
        super("IRON_HELMET",
                Material.IRON_HELMET,
                ItemFamily.IRON,
                "Iron Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0),
                ""
        );
    }
}
