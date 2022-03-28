package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum Rarity {
    COMMON(1, ChatColor.WHITE),
    UNCOMMON(2, ChatColor.GREEN),
    RARE(3, ChatColor.BLUE),
    EPIC(4, ChatColor.DARK_PURPLE),
    LEGENDARY(5, ChatColor.GOLD),
    MYTHIC(6, ChatColor.LIGHT_PURPLE),
    DIVINE(7, ChatColor.AQUA),
    SPECIAL(8, ChatColor.RED);

    private final int level;
    private final ChatColor color;

    Rarity(int level, ChatColor color) {
        this.level = level;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getColor() + "" + ChatColor.BOLD + this.name();
    }
}
