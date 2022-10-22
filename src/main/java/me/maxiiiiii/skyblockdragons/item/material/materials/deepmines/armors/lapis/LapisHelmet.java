package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.lapis;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class LapisHelmet extends ArmorMaterial {
    public LapisHelmet() {
        super("LAPIS_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.LAPIS,
                "Lapis Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0),
                ""
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(55, 55, 215);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
