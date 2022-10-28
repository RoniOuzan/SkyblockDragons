package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.chlorophyte;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.DeeperMinesFullSet;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class ChlorophyteChestplate extends ArmorMaterial {
    public ChlorophyteChestplate() {
        super("CHLOROPHYTE_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.CHLOROPHYTE,
                "Chlorophyte Chestplate",
                ItemType.CHESTPLATE,
                Rarity.UNCOMMON,
                new Stats(65, 45, 0, 20, 125, 35, 0),
                "",
                new DeeperMinesFullSet(10)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(20, 205, 0);
    }
}