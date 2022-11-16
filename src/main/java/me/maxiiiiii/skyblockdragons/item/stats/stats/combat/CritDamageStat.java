package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CritDamageStat extends CombatStat implements PercentageStat {
    public CritDamageStat() {
        super("Crit Damage",
                "☠",
                ChatColor.BLUE,
                "",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 4);
    }
}
