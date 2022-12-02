package me.maxiiiiii.skyblockdragons.item.material.materials.farming.hoes.diamond;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

import java.util.Arrays;

public class DiamondHoeTier3 extends FarmingMaterial {
    public DiamondHoeTier3() {
        super("DIAMOND_HOE_3",
                Material.DIAMOND_HOE,
                ItemFamily.DIAMOND,
                "Diamond Hoe Tier 3",
                ItemType.HOE,
                Rarity.UNCOMMON,
                new Stats(Arrays.asList(new Stat(StatTypes.FARMING_FORTUNE, 150))),
                1,
                ""
        );
        cropAdder.put(Material.CROPS, new BlockItemDrop(Items.get("WHEAT"), 5));
        cropAdder.put(Material.CARROT, new BlockItemDrop(Items.get("CARROT_ITEM"), 5));
        cropAdder.put(Material.POTATO, new BlockItemDrop(Items.get("POTATO_ITEM"), 5));
        cropAdder.put(Material.BEETROOT_BLOCK, new BlockItemDrop(Items.get("BEETROOT"), 3));
    }
}
