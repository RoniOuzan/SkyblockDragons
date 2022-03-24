package me.maxiiiiii.skyblockdragons.itemcreator;

import lombok.Getter;
import org.bukkit.ChatColor;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public enum Stat {
    HEALTH(ChatColor.RED + "❤", ChatColor.RED + "❤ Health", false),
    DEFENSE(ChatColor.GREEN + "❈", ChatColor.GREEN + "❈ Defense", false),
    STRENGTH(ChatColor.RED + "❁", ChatColor.RED + "❁ Strength", true),
    DAMAGE(ChatColor.RED + "❁", ChatColor.RED + "❁ Damage", true),
    SPEED(ChatColor.WHITE + "✦", ChatColor.WHITE + "✦ Speed", false),
    CRIT_CHANCE(ChatColor.BLUE + "☣", ChatColor.BLUE + "☣ Crit Chance", true),
    CRIT_DAMAGE(ChatColor.BLUE + "☠", ChatColor.BLUE + "☠ Crit Damage", true),
    MANA(ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Mana", false),
    INTELLIGENCE(ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Intelligence", false),
    ATTACK_SPEED(ChatColor.YELLOW + "✎", ChatColor.YELLOW + "✎ Attack Speed", true),
    FEROCITY(ChatColor.RED + "⫽", ChatColor.RED + "⫽ Ferocity", true);

    private final String icon;
    private final String iconAndText;
    private final boolean damageStat;

    Stat(String icon, String iconAndText, boolean damageStat) {
        this.icon = icon;
        this.iconAndText = iconAndText;
        this.damageStat = damageStat;
    }

    @Override
    public String toString() {
        return setTitleCase(this.name().replaceAll("_", " "));
    }
}