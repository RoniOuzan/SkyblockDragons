package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CritChanceStat extends CombatStat implements PercentageStat {
    public CritChanceStat() {
        super("Crit Chance",
                "☣",
                ChatColor.BLUE,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 4);
    }
}
