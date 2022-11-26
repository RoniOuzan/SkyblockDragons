package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TrueDefenseStat extends CombatStat {
    public TrueDefenseStat() {
        super("True Defense",
                "❂",
                ChatColor.WHITE,
                "True Defense is defense which works against true damage.",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.IRON_BLOCK);
    }
}
