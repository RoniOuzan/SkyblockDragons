package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.hematite;

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

public class HematiteBoots extends ArmorMaterial {
    public HematiteBoots() {
        super("HEMATITE_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.HEMATITE  ,
                "Hematite Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(105, 85, 40, 30, 260, 90, 10),
                "",
                new DeeperMinesFullSet(30)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(40, 0, 0);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
