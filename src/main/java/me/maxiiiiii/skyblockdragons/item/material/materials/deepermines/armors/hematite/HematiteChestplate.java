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

public class HematiteChestplate extends ArmorMaterial {
    public HematiteChestplate() {
        super("HEMATITE_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.HEMATITE,
                "Hematite Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(130, 105, 80, 30, 310, 110, 10),
                "",
                new DeeperMinesFullSet(30)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(100, 0, 0);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
