package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.chain;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class ChainChestplate extends ArmorMaterial {
    public ChainChestplate() {
        super("CHAIN_CHESTPLATE",
                Material.CHAINMAIL_CHESTPLATE,
                ItemFamily.CHAIN,
                "Chain Chestplate",
                ItemType.CHESTPLATE,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0),
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
