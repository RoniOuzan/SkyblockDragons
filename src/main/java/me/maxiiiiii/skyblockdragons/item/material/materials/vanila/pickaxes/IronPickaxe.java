package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronPickaxe extends MiningMaterial {
    public IronPickaxe() {
        super("IRON_PICKAXE",
                Material.IRON_PICKAXE,
                ItemFamily.IRON,
                "Iron Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new Stats(70, 0),
                3,
                ""
        );
    }
}
