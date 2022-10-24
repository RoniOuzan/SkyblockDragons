package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class IronPickaxe extends MiningMaterial {
    public IronPickaxe() {
        super("IRON_PICKAXE",
                Material.IRON_PICKAXE,
                ItemFamily.IRON,
                "Iron Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new Stats(120, 0),
                3,
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
