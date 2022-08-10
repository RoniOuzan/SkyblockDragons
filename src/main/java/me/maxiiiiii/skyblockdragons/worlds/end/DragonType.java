package me.maxiiiiii.skyblockdragons.worlds.end;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

public enum DragonType {
    OLD(ChatColor.YELLOW),
    PROTECTOR(ChatColor.GRAY),
    WISE(ChatColor.AQUA),
    UNSTABLE(ChatColor.DARK_PURPLE),
    YOUNG(ChatColor.GREEN),
    STRONG(ChatColor.RED),
    SUPERIOR(ChatColor.GOLD),
    ERROR(ChatColor.RED)
    ;

    public ChatColor color;

    DragonType(ChatColor color) {
        this.color = color;
    }

    public static DragonType getDragonType(String name) {
        for (DragonType dragonType : DragonType.values()) {
            if (name.toUpperCase().contains(dragonType.name()))
                return dragonType;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.color + "" + ChatColor.BOLD + Functions.setTitleCase(this.name());
    }
}
