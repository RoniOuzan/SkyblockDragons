package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class StonePickaxe extends MiningMaterial {
    public StonePickaxe() {
        super("STONE_PICKAXE",
                Material.STONE_PICKAXE,
                ItemFamily.STONE,
                "Stone Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new Stats(80, 0),
                2,
                ""
        );
    }
}
