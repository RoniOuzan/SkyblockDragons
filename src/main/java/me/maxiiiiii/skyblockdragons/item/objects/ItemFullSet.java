package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum ItemFullSet {
    WITHER_ARMOR("Witherborn", "Spawns a wither minion every " + ChatColor.YELLOW + "30 " + ChatColor.GRAY + "seconds up to a maximum " + ChatColor.GREEN + "1 " + ChatColor.GRAY + "wither. Your withers will travel to and explode on nearby enemies.", 30),
    SUPERIOR_DRAGON("Superior blood", "Most of your stats are increased by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Aspect of the " + ChatColor.GOLD + "Dragons " + ChatColor.GRAY + "ability deals " + ChatColor.GREEN + "50% " + ChatColor.GRAY + "more damage.", 0),
    PIGMAN("Burning", "Deal " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.GREEN + "Pigman Dagger" + ChatColor.GRAY + ".", 0),
    NULL("", "", 0);

    private final String name;
    private final String description;
    private final int cooldown;

    ItemFullSet(String name, String description, int cooldown) {
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
    }
}
