package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.leather;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import org.bukkit.Material;

public class LeatherLeggings extends ArmorMaterial {
    public LeatherLeggings() {
        super("LEATHER_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.LEATHER,
                "Leather Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0),
                ""
        );
    }
}