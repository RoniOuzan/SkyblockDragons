package me.maxiiiiii.skyblockdragons.item.stats.interfaces;

import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.function.Function;

public abstract class MiscStat extends StatType {
    public MiscStat(String name, String icon, ChatColor color, String description, Function<PlayerSD, Double> maxLevel, Function<PlayerSD, Double> base) {
        super(name, icon, color, description, maxLevel, base);
    }

    public MiscStat(String name, String icon, ChatColor color, String description, double base) {
        super(name, icon, color, description, base);
    }

    public MiscStat(String name, String icon, ChatColor color, String description) {
        super(name, icon, color, description);
    }
}