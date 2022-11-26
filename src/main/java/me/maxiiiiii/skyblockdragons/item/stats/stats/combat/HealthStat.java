package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HealthStat extends CombatStat {
    public HealthStat() {
        super("Health",
                "❤",
                ChatColor.RED,
                "Your Health Stat increases your maximum health.",
                100
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLDEN_APPLE);
    }
}
