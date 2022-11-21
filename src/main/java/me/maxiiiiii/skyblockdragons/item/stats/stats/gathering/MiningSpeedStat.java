package me.maxiiiiii.skyblockdragons.item.stats.stats.gathering;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.GatheringStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MiningSpeedStat extends GatheringStat {
    public MiningSpeedStat() {
        super("Mining Speed",
                "⸕",
                ChatColor.GOLD,
                ""
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.IRON_PICKAXE);
    }
}
