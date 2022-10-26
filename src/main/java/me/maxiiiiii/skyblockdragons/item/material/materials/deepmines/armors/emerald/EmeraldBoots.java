package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.emerald;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import org.bukkit.Color;
import org.bukkit.Material;

public class EmeraldBoots extends ArmorMaterial {
    public EmeraldBoots() {
        super("EMERALD_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.EMERALD,
                "Emerald Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(5, 240, 15);
    }
}
