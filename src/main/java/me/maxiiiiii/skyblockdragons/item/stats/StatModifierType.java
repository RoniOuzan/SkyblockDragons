package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.function.BiFunction;

@Getter
public enum StatModifierType {
    HOT_POTATO(1, (t, s) -> ChatColor.YELLOW + "(" + getNum(s) + ")"),
    REFORGE(2, (t, s) -> ChatColor.BLUE + "(" + t + " " + getNum(s) + ")"),
    ;

    private final int priority;
    private final BiFunction<String, Stat, String> text;

    StatModifierType(int priority, BiFunction<String, Stat, String> text) {
        this.priority = priority;
        this.text = text;
    }

    private static String getNum(Stat stat) {
        return (stat.get() < 0 ? "-" : "+") + Math.abs(stat.get()) + (stat.getType().isPercentage() ? "%" : "");
    }
}
