package me.maxiiiiii.skyblockdragons.item.material.materials.hub.tools;

import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAttached;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.GatheringStats;
import org.bukkit.Material;

public class CobblestonePickaxe extends MiningMaterial implements ItemAttached {
    public CobblestonePickaxe() {
        super("COBBLESTONE_PICKAXE",
                Material.STONE_PICKAXE,
                ItemFamily.COBBLESTONE_PICKAXE,
                "Cobblestone Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new GatheringStats(40, 0, 0, 0),
                2,
                ""
        );
    }
}
