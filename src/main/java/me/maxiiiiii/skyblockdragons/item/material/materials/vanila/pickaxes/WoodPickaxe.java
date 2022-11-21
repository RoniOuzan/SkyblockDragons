package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class WoodPickaxe extends MiningMaterial {
    public WoodPickaxe() {
        super("WOOD_PICKAXE",
                Material.WOOD_PICKAXE,
                ItemFamily.WOOD,
                "Wood Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new Stats(50, 0),
                1,
                ""
        );
    }
}
