package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SeaCreatureChanceStat extends MiscStat implements PercentageStat {
    public SeaCreatureChanceStat() {
        super("Sea Creature Chance",
                "α",
                ChatColor.DARK_AQUA,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.RAW_FISH);
    }
}
