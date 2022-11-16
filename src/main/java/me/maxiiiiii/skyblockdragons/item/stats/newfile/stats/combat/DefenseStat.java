package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DefenseStat extends CombatStat {
    public DefenseStat() {
        super("Defense",
                "❈",
                ChatColor.GREEN,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }
}
