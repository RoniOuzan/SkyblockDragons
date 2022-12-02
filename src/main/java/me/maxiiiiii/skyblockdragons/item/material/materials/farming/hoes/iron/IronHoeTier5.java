package me.maxiiiiii.skyblockdragons.item.material.materials.farming.hoes.iron;

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

public class IronHoeTier5 extends FarmingMaterial {
    public IronHoeTier5() {
        super("IRON_HOE_5",
                Material.IRON_HOE,
                ItemFamily.IRON,
                "Iron Hoe Tier 5",
                ItemType.HOE,
                Rarity.COMMON,
                new Stats(Arrays.asList(new Stat(StatTypes.FARMING_FORTUNE, 100))),
                1,
                ""
        );
        cropAdder.put(Material.CROPS, new BlockItemDrop(Items.get("WHEAT"), 5));
        cropAdder.put(Material.CARROT, new BlockItemDrop(Items.get("CARROT_ITEM"), 5));
        cropAdder.put(Material.POTATO, new BlockItemDrop(Items.get("POTATO_ITEM"), 5));
    }
}
