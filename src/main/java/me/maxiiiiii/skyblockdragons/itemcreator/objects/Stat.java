package me.maxiiiiii.skyblockdragons.itemcreator.objects;

import lombok.Getter;
import org.bukkit.ChatColor;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public enum Stat {
    DAMAGE(ChatColor.RED + "❁", ChatColor.RED + "❁ Damage", true),
    STRENGTH(ChatColor.RED + "❁", ChatColor.RED + "❁ Strength", true),
    CRIT_DAMAGE(ChatColor.BLUE + "☠", ChatColor.BLUE + "☠ Crit Damage", true),
    CRIT_CHANCE(ChatColor.BLUE + "☣", ChatColor.BLUE + "☣ Crit Chance", true),
    ABILITY_DAMAGE(ChatColor.RED + "๑", ChatColor.RED + "๑ Ability Damage", true),
    ABILITY_SCALING(ChatColor.RED + "๑", ChatColor.RED + "๑ Ability Scaling", true),
    ATTACK_SPEED(ChatColor.YELLOW + "✎", ChatColor.YELLOW + "✎ Attack Speed", true),
    FEROCITY(ChatColor.RED + "⫽", ChatColor.RED + "⫽ Ferocity", true),
    HEALTH(ChatColor.RED + "❤", ChatColor.RED + "❤ Health", false),
    DEFENSE(ChatColor.GREEN + "❈", ChatColor.GREEN + "❈ Defense", false),
    SPEED(ChatColor.WHITE + "✦", ChatColor.WHITE + "✦ Speed", false),
    MANA(ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Mana", false),
    INTELLIGENCE(ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Intelligence", false),
    ;

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