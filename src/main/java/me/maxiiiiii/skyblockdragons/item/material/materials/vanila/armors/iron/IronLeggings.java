package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronLeggings extends ArmorMaterial {
    public IronLeggings() {
        super("IRON_LEGGINGS",
                Material.IRON_LEGGINGS,
                ItemFamily.IRON,
                "Iron Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0),
                ""
        );
    }
}
