package me.maxiiiiii.skyblockdragons.item.stats.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FishingSpeedStat extends MiscStat implements PercentageStat {
    public FishingSpeedStat() {
        super("Fishing Speed",
                "☂",
                ChatColor.AQUA,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.FISHING_ROD);
    }
}
