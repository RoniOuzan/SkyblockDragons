package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AttackSpeedStat extends CombatStat implements PercentageStat {
    public AttackSpeedStat() {
        super("Attack Speed",
                "⚔",
                ChatColor.YELLOW,
                "Attack Speed decreases the time between hits on your opponent.",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_AXE);
    }
}
