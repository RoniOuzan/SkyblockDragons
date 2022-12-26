package me.maxiiiiii.skyblockdragons.item.material.materials.theend.tools;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.GatheringStats;
import org.bukkit.Material;

public class EndStonePickaxe extends MiningMaterial {
    public EndStonePickaxe() {
        super("ENDSTONE_PICKAXE",
                Material.GOLD_PICKAXE,
                ItemFamily.ENDSTONE,
                "Endstone Pickaxe",
                ItemType.PICKAXE,
                Rarity.EPIC,
                new GatheringStats(320, 40, 0, 0),
                4,
                ""
        );
    }
}
