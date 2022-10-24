package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class StonePickaxe extends MiningMaterial {
    public StonePickaxe() {
        super("STONE_PICKAXE",
                Material.STONE_PICKAXE,
                ItemFamily.STONE,
                "Stone Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new Stats(80, 0),
                2,
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
