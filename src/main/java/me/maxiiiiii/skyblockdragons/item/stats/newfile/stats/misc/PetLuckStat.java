package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.misc;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PetLuckStat extends MiscStat implements PercentageStat {
    public PetLuckStat() {
        super("Pet Luck",
                "♣",
                ChatColor.LIGHT_PURPLE,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 5);
    }
}
