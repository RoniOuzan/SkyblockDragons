package me.maxiiiiii.skyblockdragons.item.stats.stats.gathering;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.GatheringStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ForagingFortuneStat extends GatheringStat {
    public ForagingFortuneStat() {
        super("Foraging Fortune",
                "☘",
                ChatColor.GOLD,
                "Foraging Fortune is the chance to gain multiple drops from logs"
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GOLD_AXE);
    }
}
