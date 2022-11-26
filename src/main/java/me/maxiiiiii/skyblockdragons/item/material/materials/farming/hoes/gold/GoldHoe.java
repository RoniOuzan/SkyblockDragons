package me.maxiiiiii.skyblockdragons.item.material.materials.farming.hoes.gold;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

import java.util.Arrays;

public class GoldHoe extends FarmingMaterial {
    public GoldHoe() {
        super("GOLD_HOE",
                Material.GOLD_HOE,
                ItemFamily.GOLD,
                "Gold Hoe",
                ItemType.HOE,
                Rarity.RARE,
                new Stats(Arrays.asList(new Stat(StatTypes.FARMING_FORTUNE, 200))),
                1,
                ""
        );
        cropAdder.put(Material.CROPS, new BlockItemDrop(Items.get("WHEAT"), 5));
        cropAdder.put(Material.CARROT, new BlockItemDrop(Items.get("CARROT_ITEM"), 5));
        cropAdder.put(Material.POTATO, new BlockItemDrop(Items.get("POTATO_ITEM"), 5));
        cropAdder.put(Material.BEETROOT_BLOCK, new BlockItemDrop(Items.get("BEETROOT"), 5));
    }
}
