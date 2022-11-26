package me.maxiiiiii.skyblockdragons.item.stats.stats.gathering;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.GatheringStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FarmingFortuneStat extends GatheringStat {
    public FarmingFortuneStat() {
        super("Farming Fortune",
                "☘",
                ChatColor.GOLD,
                "Farming Fortune is the chance to gain multiple drops from crops."
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_HOE);
    }
}
