package me.maxiiiiii.skyblockdragons.item.stats.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SeaCreatureChanceStat extends MiscStat implements PercentageStat {
    public SeaCreatureChanceStat() {
        super("Sea Creature Chance",
                "α",
                ChatColor.DARK_AQUA,
                "Sea Creature Chance is your chance to catch Sea Creatures while fishing."
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.RAW_FISH);
    }
}
