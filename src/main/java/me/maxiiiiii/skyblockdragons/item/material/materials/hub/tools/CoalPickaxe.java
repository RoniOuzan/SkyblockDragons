package me.maxiiiiii.skyblockdragons.item.material.materials.hub.tools;

import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAttached;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.GatheringStats;
import org.bukkit.Material;

public class CoalPickaxe extends MiningMaterial implements ItemAttached {
    public CoalPickaxe() {
        super("COAL_PICKAXE",
                Material.STONE_PICKAXE,
                ItemFamily.COBBLESTONE_PICKAXE,
                "Coal Pickaxe",
                ItemType.PICKAXE,
                Rarity.UNCOMMON,
                new GatheringStats(50, 0, 0, 0),
                3,
                ""
        );
    }
}
