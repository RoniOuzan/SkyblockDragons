package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.emerald;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSet;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class EmeraldChestplate extends ArmorMaterial {
    public EmeraldChestplate() {
        super("EMERALD_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.EMERALD,
                "Emerald Chestplate",
                ItemType.CHESTPLATE,
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
