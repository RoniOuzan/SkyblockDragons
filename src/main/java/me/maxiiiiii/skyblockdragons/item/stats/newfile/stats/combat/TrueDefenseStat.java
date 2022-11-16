package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TrueDefenseStat extends CombatStat {
    public TrueDefenseStat() {
        super("True Defense",
                "❂",
                ChatColor.WHITE,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.IRON_BLOCK);
    }
}
