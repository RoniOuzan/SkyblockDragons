package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.dernic;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.DeeperMinesFullSet;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSet;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class DernicChestplate extends ArmorMaterial {
    public DernicChestplate() {
        super("DERNIC_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.LUMINATE,
                "Dernic Chestplate",
                ItemType.CHESTPLATE,
                Rarity.EPIC,
                new Stats(100, 105, 0, 30, 205, 80, 5),
                "",
                new DeeperMinesFullSet(20)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(190, 100, 35);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
