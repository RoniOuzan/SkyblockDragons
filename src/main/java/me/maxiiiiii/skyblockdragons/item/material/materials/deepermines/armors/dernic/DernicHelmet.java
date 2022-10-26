package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.dernic;

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

public class DernicHelmet extends ArmorMaterial {
    public DernicHelmet() {
        super("DERNIC_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.LUMINATE,
                "Dernic Helmet",
                ItemType.HELMET,
                Rarity.EPIC,
                new Stats(85, 90, 0, 30, 190, 65, 5),
                "",
                new DeeperMinesFullSet(20)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(200, 105, 40);
    }
}
