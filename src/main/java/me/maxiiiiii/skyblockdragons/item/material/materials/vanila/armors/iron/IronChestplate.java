package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronChestplate extends ArmorMaterial {
    public IronChestplate() {
        super("IRON_CHESTPLATE",
                Material.IRON_CHESTPLATE,
                ItemFamily.IRON,
                "Iron Chestplate",
                ItemType.CHESTPLATE,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0),
                ""
        );
    }
}
