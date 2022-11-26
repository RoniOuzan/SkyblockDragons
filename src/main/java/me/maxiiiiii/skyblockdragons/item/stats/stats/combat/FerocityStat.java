package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FerocityStat extends CombatStat implements PercentageStat {
    public FerocityStat() {
        super("Ferocity",
                "๑",
                ChatColor.RED,
                "Ferocity grants percent chance to double-strike enemies. Increments of 100 increases the base number of strikes.",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 1);
    }
}
