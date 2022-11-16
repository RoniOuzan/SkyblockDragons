package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ManaStat extends CombatStat {
    public ManaStat() {
        super("Mana",
                "✎",
                ChatColor.AQUA,
                "",
                100
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.ENCHANTED_BOOK);
    }
}
