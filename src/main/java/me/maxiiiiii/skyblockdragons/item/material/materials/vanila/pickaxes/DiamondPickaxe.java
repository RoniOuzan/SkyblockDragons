package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class DiamondPickaxe extends MiningMaterial {
    public DiamondPickaxe() {
        super("DIAMOND_PICKAXE",
                Material.DIAMOND_PICKAXE,
                ItemFamily.DIAMOND,
                "Diamond Pickaxe",
                ItemType.PICKAXE,
                Rarity.UNCOMMON,
                new Stats(230, 0),
                4,
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
