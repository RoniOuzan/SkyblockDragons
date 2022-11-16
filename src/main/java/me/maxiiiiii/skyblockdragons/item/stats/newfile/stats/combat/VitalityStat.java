package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VitalityStat extends CombatStat {
    public VitalityStat() {
        super("Vitality",
                "♨",
                ChatColor.DARK_RED,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.REDSTONE);
    }
}
