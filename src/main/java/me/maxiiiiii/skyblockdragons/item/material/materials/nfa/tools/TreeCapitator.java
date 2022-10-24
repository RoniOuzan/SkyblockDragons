package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.tools;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class TreeCapitator extends MiningMaterial {
    public TreeCapitator() {
        super("TREE_CAPITATOR",
                Material.GOLD_AXE,
                ItemFamily.JUNGLE_AXE,
                "Tree Capitator",
                ItemType.AXE,
                Rarity.EPIC,
                1,
                "A forceful Gold Axe which can break a large amount of logs in a single hit!"
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
