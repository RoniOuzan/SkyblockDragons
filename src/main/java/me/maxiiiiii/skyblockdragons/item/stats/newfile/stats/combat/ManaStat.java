package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ManaStat extends CombatStat {
    public ManaStat() {
        super("Mana",
                "✎",
                ChatColor.AQUA,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.ENCHANTED_BOOK);
    }
}
