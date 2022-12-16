package me.maxiiiiii.skyblockdragons.world.worlds.bearisland;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.worlds.end.DragonType;
import org.bukkit.ChatColor;

public enum BearType {
    GRIZZLY_BEAR(ChatColor.GOLD),
    POLAR_BEAR(ChatColor.GOLD),
    KOALA_BEAR(ChatColor.GOLD),
    PANDA_BEAR(ChatColor.GOLD),
    RED_PANDA_BEAR(ChatColor.GOLD),
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
