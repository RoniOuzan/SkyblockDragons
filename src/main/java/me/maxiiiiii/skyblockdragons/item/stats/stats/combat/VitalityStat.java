package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VitalityStat extends CombatStat {
    public VitalityStat() {
        super("Vitality",
                "♨",
                ChatColor.DARK_RED,
                "Vitality increases the amount of health that you regenerate over time.",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.REDSTONE);
    }
}
