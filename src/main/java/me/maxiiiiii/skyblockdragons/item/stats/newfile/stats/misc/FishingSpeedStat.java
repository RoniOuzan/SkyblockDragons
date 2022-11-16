package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FishingSpeedStat extends MiscStat implements PercentageStat {
    public FishingSpeedStat() {
        super("Fishing SPeed",
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
