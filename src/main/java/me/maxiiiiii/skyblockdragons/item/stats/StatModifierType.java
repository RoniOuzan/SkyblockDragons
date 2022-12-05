package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

import java.util.function.BiFunction;

@Getter
public enum StatModifierType {
    HOT_POTATO(1, (t, s) -> ChatColor.YELLOW + "(" + Functions.getNumSymbol(s) + ")"),
    REFORGE(2, (t, s) -> ChatColor.BLUE + "(" + t + " " + Functions.getNumSymbol(s) + ")"),
    CRYSTAL(3, (t, s) -> ChatColor.LIGHT_PURPLE + "(" + Functions.getNumSymbol(s) + ")"),
    ENCHANT(4, (t, s) -> ChatColor.BLUE + "(" + t + " " + Functions.getNumSymbol(s) + ")"),
    ;

    private final int priority;
    private final BiFunction<String, Stat, String> text;

    StatModifierType(int priority, BiFunction<String, Stat, String> text) {
        this.priority = priority;
        this.text = text;
    }

}
