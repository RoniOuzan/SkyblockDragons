package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.gold;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import org.bukkit.Material;

public class GoldLeggings extends ArmorMaterial {
    public GoldLeggings() {
        super("GOLD_LEGGINGS",
                Material.GOLD_LEGGINGS,
                ItemFamily.GOLD,
                "Gold Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0),
                ""
        );
    }
}
