package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum ItemFullSet {
    WITHER_ARMOR(4, "Witherborn", "Spawns a wither minion every " + ChatColor.YELLOW + "30 " + ChatColor.GRAY + "seconds up to a maximum " + ChatColor.GREEN + "1 " + ChatColor.GRAY + "wither. Your withers will travel to and explode on nearby enemies.", 30),
    SUPERIOR_DRAGON(4, "Superior blood", "Most of your stats are increased by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Aspect of the " + ChatColor.GOLD + "Dragons " + ChatColor.GRAY + "ability deals " + ChatColor.GREEN + "50% " + ChatColor.GRAY + "more damage.", 0),
    STRONG_DRAGON(4, "Strong blood", "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.BLUE + "Aspect of The End" + ChatColor.GRAY + ".", 0),
    YOUNG_DRAGON(4, "Young blood", "Gain " + ChatColor.WHITE + "+70" + StatType.SPEED.getIconAndText() + " " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY + ".", 0),
    UNSTABLE_DRAGON(4, "Unstable blood", "Sometimes strikes nearby mobs with lightning.", 0),
    WISE_DRAGON(4, "Wise blood", "Abilities have " + ChatColor.GREEN + "60% " + ChatColor.GRAY + "of the mana cost.", 0),
    PROTECTOR_DRAGON(4, "Protector blood", "Increases " + StatType.DEFENSE.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "30% " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY + ".", 0),
    OLD_DRAGON(4, "Old blood", "Increases the " + StatType.HEALTH.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "20%" + ChatColor.GRAY + ".", 0),

    PIGMAN(4, "Burning", "Deal " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.GREEN + "Pigman Dagger" + ChatColor.GRAY + ".", 0),
    COBALT(4, "Cobalt", "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "5%" + ChatColor.GRAY + ".", 0),
    CHLOROPHYTE(4, "Chlorophyte", "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "10%" + ChatColor.GRAY + ".", 0),
    LUMINATE(4, "Luminate", "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "15%" + ChatColor.GRAY + ".", 0),
    DERNIC(4, "Dernic", "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "20%" + ChatColor.GRAY + ".", 0),
    HEMATITE(4, "Hematite", "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "30%" + ChatColor.GRAY + ".", 0),
    VOID_CRYSTAL(4, "Void Crystal", "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "50%" + ChatColor.GRAY + ".", 0),

    NULL(Integer.MAX_VALUE, "", "", 0);

    private final int amountOfPieces;
    private final String name;
    private final String description;
    private final int cooldown;

    ItemFullSet(int amountOfPieces, String name, String description, int cooldown) {
        this.amountOfPieces = amountOfPieces;
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
    }
}
