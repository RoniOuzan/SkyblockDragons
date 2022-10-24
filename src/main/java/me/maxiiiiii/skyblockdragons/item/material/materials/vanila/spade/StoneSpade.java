package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.spade;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class StoneSpade extends MiningMaterial {
    public StoneSpade() {
        super("STONE_SPADE",
                Material.STONE_SPADE,
                ItemFamily.STONE,
                "Stone Spade",
                ItemType.SPADE,
                Rarity.COMMON,
                new Stats(80, 0),
                1,
                ""
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
