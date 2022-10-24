package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.tools;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class WorldEater extends MiningMaterial {
    public WorldEater() {
        super("WORLD_EATER",
                Material.DIAMOND_PICKAXE,
                ItemFamily.JUNGLE_AXE,
                "World Eater",
                ItemType.PICKAXE,
                Rarity.DIVINE,
                10,
                ChatColor.ITALIC + "I became a world ERROR."
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
