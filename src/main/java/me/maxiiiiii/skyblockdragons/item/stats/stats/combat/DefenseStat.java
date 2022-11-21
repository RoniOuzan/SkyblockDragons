package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DefenseStat extends CombatStat {
    public DefenseStat() {
        super("Defense",
                "❈",
                ChatColor.GREEN,
                "",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }
}
