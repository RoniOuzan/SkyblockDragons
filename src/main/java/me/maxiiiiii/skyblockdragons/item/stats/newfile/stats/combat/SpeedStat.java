package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SpeedStat extends CombatStat {
    public SpeedStat() {
        super("Speed",
                "✦",
                ChatColor.WHITE,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.SUGAR);
    }
}
