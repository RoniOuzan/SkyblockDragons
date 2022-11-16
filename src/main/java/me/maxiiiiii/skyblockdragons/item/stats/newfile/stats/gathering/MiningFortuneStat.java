package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.gathering;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.GatheringStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MiningFortuneStat extends GatheringStat {
    public MiningFortuneStat() {
        super("Mining Fortune",
                "☘",
                ChatColor.GOLD,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_PICKAXE);
    }
}
