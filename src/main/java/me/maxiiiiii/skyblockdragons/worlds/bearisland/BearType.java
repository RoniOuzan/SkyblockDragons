package me.maxiiiiii.skyblockdragons.worlds.bearisland;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

public enum BearType {
    GRIZZLY_BEAR(ChatColor.GOLD),
    PANDA_BEAR(ChatColor.WHITE),
    POLAR_BEAR(ChatColor.WHITE),
    KOALA_BEAR(ChatColor.BLACK),
    RED_PANDA_BEAR(ChatColor.RED),
    ;

    public ChatColor color;

    BearType(ChatColor color) {
        this.color = color;
    }

    public static BearType getBearType(String name) {
        for (BearType bearType : BearType.values()) {
            if (name.toUpperCase().contains(bearType.name()))
                return bearType;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.color + "" + ChatColor.BOLD + Functions.setTitleCase(this.name());
    }
}
