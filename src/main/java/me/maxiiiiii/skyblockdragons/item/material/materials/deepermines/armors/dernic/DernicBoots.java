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

public class DernicBoots extends ArmorMaterial {
    public DernicBoots() {
        super("DERNIC_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.LUMINATE,
                "Dernic Boots",
                ItemType.BOOTS,
                Rarity.EPIC,
                new Stats(80, 85, 0, 30, 180, 60, 5),
                "",
                new DeeperMinesFullSet(20)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(130, 85, 25);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
