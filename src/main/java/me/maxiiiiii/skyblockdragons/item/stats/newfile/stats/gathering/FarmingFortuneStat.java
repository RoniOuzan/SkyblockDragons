package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.gathering;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.GatheringStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FarmingFortuneStat extends GatheringStat {
    public FarmingFortuneStat() {
        super("Farming Fortune",
                "☘",
                ChatColor.GOLD,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_HOE);
    }
}
