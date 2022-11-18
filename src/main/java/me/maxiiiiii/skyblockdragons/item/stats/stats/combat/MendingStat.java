package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MendingStat extends CombatStat {
    public MendingStat() {
        super("Mending",
                "☄",
                ChatColor.DARK_AQUA,
                "",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.INK_SACK, 1, (short) 6);
    }
}
