package me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.function.Function;

public abstract class GatheringStat extends StatType {
    public GatheringStat(String name, String icon, ChatColor color, String description, Function<PlayerSD, Double> maxLevel) {
        super(name, icon, color, description, maxLevel);
    }

    public GatheringStat(String name, String icon, ChatColor color, String description) {
        super(name, icon, color, description);
    }
}
