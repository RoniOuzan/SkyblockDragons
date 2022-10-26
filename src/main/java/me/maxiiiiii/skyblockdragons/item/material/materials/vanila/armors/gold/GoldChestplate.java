package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.gold;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import org.bukkit.Material;

public class GoldChestplate extends ArmorMaterial {
    public GoldChestplate() {
        super("GOLD_CHESTPLATE",
                Material.GOLD_CHESTPLATE,
                ItemFamily.GOLD,
                "Gold Chestplate",
                ItemType.CHESTPLATE,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0),
                ""
        );
    }
}
