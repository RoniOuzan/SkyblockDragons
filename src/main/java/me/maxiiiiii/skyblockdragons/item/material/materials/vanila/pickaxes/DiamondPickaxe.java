package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
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
}
