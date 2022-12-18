package me.maxiiiiii.skyblockdragons.item.stats.stats.gathering;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.GatheringStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class MiningFortuneStat extends GatheringStat implements Listener {
    public MiningFortuneStat() {
        super("Mining Fortune",
                "☘",
                ChatColor.GOLD,
                "Mining Fortune is the chance to get multiple drops from ores."
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_PICKAXE);
    }
}
