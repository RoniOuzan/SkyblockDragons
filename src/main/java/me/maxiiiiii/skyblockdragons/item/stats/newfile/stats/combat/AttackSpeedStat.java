package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AttackSpeedStat extends CombatStat implements PercentageStat {
    public AttackSpeedStat() {
        super("Attack Speed",
                "⚔",
                ChatColor.YELLOW,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_AXE);
    }
}
