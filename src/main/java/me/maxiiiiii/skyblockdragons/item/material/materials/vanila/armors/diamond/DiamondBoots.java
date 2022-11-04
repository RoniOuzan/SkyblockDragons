package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.diamond;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class DiamondBoots extends ArmorMaterial {
    public DiamondBoots() {
        super("DIAMOND_BOOTS",
                Material.DIAMOND_BOOTS,
                ItemFamily.DIAMOND,
                "Diamond Boots",
                ItemType.BOOTS,
                Rarity.UNCOMMON,
                new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0),
                ""
        );
    }
}
