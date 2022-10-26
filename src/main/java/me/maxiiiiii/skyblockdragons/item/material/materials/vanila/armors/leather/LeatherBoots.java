package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.leather;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import org.bukkit.Material;

public class LeatherBoots extends ArmorMaterial {
    public LeatherBoots() {
        super("LEATHER_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.LEATHER,
                "Leather Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0),
                ""
        );
    }
}
