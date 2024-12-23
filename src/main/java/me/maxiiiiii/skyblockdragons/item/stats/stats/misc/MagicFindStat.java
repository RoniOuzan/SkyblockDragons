package me.maxiiiiii.skyblockdragons.item.stats.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MagicFindStat extends MiscStat implements PercentageStat {
    public MagicFindStat() {
        super("Magic Find",
                "✯",
                ChatColor.AQUA,
                "Magic Find increases how many rare items you find."
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 6);
    }
}
