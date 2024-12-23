package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.diamond;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class DiamondChestplate extends ArmorMaterial {
    public DiamondChestplate() {
        super("DIAMOND_CHESTPLATE",
                Material.DIAMOND_CHESTPLATE,
                ItemFamily.DIAMOND,
                "Diamond Chestplate",
                ItemType.CHESTPLATE,
                Rarity.UNCOMMON,
                new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0),
                ""
        );
    }
}
