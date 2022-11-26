package me.maxiiiiii.skyblockdragons.item.stats.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.MiscStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SpeedStat extends MiscStat {
    public SpeedStat() {
        super("Speed",
                "✦",
                ChatColor.WHITE,
                "Your Speed stat increases how fast you can walk.",
                100
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.SUGAR);
    }
}
