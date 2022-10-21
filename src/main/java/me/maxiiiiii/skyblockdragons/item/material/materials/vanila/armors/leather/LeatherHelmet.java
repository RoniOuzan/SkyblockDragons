package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.leather;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class LeatherHelmet extends ArmorMaterial {
    public LeatherHelmet() {
        super("LEATHER_HELMET",
                Material.LEATHER_HELMET,
                ItemFamily.LEATHER,
                "Leather Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0),
                "",
                ItemFullSet.NULL
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
