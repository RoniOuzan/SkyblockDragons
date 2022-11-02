package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.leather;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import org.bukkit.Material;

public class LeatherChestplate extends ArmorMaterial {
    public LeatherChestplate() {
        super("LEATHER_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.LEATHER,
                "Leather Chestplate",
                ItemType.CHESTPLATE,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0),
                ""
        );
    }
}
