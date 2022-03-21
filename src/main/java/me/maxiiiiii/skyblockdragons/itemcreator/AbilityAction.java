package me.maxiiiiii.skyblockdragons.itemcreator;

import org.bukkit.ChatColor;

public enum AbilityAction {
    LEFT_CLICK,
    RIGHT_CLICK,
    LEFT_SHIFT_CLICK,
    RIGHT_SHIFT_CLICK,
    FULL_SET,
    NONE,
    NULL;

    @Override
    public String toString() {
        if (this == AbilityAction.NONE) {
            return "";
        }
        return ChatColor.YELLOW + "" + ChatColor.BOLD + this.name().replace("_", " ");
    }
}
