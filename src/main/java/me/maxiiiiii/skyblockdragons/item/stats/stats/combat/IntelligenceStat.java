package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.FilledStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IntelligenceStat extends CombatStat implements FilledStat {
    private final ManaStat mana;

    public IntelligenceStat() {
        super("Intelligence",
                "✎",
                ChatColor.AQUA,
                "",
                100
        );
        this.mana = new ManaStat();
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.ENCHANTED_BOOK);
    }

    @Override
    public StatType getFiller() {
        return mana;
    }
}
