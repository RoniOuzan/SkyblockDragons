package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.spade;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class IronSpade extends MiningMaterial {
    public IronSpade() {
        super("IRON_SPADE",
                Material.IRON_SPADE,
                ItemFamily.IRON,
                "Iron Spade",
                ItemType.SPADE,
                Rarity.COMMON,
                new Stats(120, 0),
                1,
                ""
        );
    }
}
