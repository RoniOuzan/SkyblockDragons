package me.maxiiiiii.skyblockdragons.item.material.materials.farming.hoes.wood;

import com.google.common.collect.Lists;
import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.util.objects.CropAdder;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class WoodHoe extends FarmingMaterial {
    public WoodHoe() {
        super("WOOD_HOE",
                Material.WOOD_HOE,
                ItemFamily.WOOD,
                "Wood Hoe Tier 1",
                ItemType.HOE,
                Rarity.COMMON,
                new Stats(Arrays.asList(new Stat(StatTypes.FARMING_FORTUNE, 20))),
                1,
                ""
        );
        cropAdder.put(Material.CROPS, new BlockItemDrop(Items.get("WHEAT"), 1));
    }
}
