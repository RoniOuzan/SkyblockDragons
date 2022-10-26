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

public class ChlorophyteBoots extends ArmorMaterial {
    public ChlorophyteBoots() {
        super("CHLOROPHYTE_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.CHLOROPHYTE,
                "Chlorophyte Boots",
                ItemType.BOOTS,
                Rarity.UNCOMMON,
                new Stats(45, 35, 0, 20, 110, 25, 0),
                "",
                new DeeperMinesFullSet(10)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(26, 255, 0);
    }
}
