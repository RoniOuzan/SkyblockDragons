package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public enum StatType {
    DAMAGE(ChatColor.RED + "❁", ChatColor.RED + "❁ Damage", false),
    STRENGTH(ChatColor.RED + "❁", ChatColor.RED + "❁ Strength", false),
    CRIT_DAMAGE(ChatColor.BLUE + "☠", ChatColor.BLUE + "☠ Crit Damage", true),
    CRIT_CHANCE(ChatColor.BLUE + "☣", ChatColor.BLUE + "☣ Crit Chance", true),
    ABILITY_DAMAGE(ChatColor.RED + "๑", ChatColor.RED + "๑ Ability Damage", true),
    ABILITY_SCALING(ChatColor.RED + "๑", ChatColor.RED + "๑ Ability Scaling", true),
    ATTACK_SPEED(ChatColor.YELLOW + "✎", ChatColor.YELLOW + "✎ Attack Speed", true),
    FEROCITY(ChatColor.RED + "⫽", ChatColor.RED + "⫽ Ferocity", false),
    HEALTH(ChatColor.RED + "❤", ChatColor.RED + "❤ Health", false),
    DEFENSE(ChatColor.GREEN + "❈", ChatColor.GREEN + "❈ Defense", false),
    TRUE_DEFENSE(ChatColor.WHITE + "❂", ChatColor.WHITE + "❂ Defense", false),
    SPEED(ChatColor.WHITE + "✦", ChatColor.WHITE + "✦ Speed", true),
    MANA(ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Mana", false),
    INTELLIGENCE(ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Intelligence", false),
    MAGIC_FIND(ChatColor.AQUA + "✯", ChatColor.AQUA + "✯ Magic Find", true),
    PET_LUCK(ChatColor.LIGHT_PURPLE + "♣", ChatColor.LIGHT_PURPLE + "♣ Pet Luck", true),
    MINING_SPEED(ChatColor.GOLD + "⸕", ChatColor.GOLD + "⸕ Mining Speed", false),
    MINING_FORTUNE(ChatColor.GOLD + "☘", ChatColor.GOLD + "☘ Mining Fortune", false),
    SEA_CREATURE_CHANCE(ChatColor.DARK_AQUA + "α", ChatColor.DARK_AQUA + "α Sea Create Chance", true),
    ABSORPTION(ChatColor.GOLD + "❤", ChatColor.GOLD + "❤ Intelligence", false),
    ;

    private final String icon;
    private final String iconAndText;
    private final boolean percentage;

    StatType(String icon, String iconAndText, boolean percentage) {
        this.icon = icon;
        this.iconAndText = iconAndText;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return setTitleCase(this.name().replaceAll("_", " "));
    }
}