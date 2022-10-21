package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.gold;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class GoldBoots extends ArmorMaterial {
    public GoldBoots() {
        super("GOLD_BOOTS",
                Material.GOLD_BOOTS,
                ItemFamily.GOLD,
                "Gold Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0),
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
