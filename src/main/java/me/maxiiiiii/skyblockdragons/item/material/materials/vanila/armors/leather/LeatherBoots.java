package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.leather;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class LeatherBoots extends ArmorMaterial {
    public LeatherBoots() {
        super("LEATHER_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.LEATHER,
                "Leather Boots",
                ItemType.BOOTS,
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
