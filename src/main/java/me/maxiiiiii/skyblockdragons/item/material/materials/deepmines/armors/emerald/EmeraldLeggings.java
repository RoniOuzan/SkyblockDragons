package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.emerald;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSet;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class EmeraldLeggings extends ArmorMaterial {
    public EmeraldLeggings() {
        super("EMERALD_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.EMERALD,
                "Emerald Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(5, 240, 15);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
