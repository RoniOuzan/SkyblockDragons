package me.maxiiiiii.skyblockdragons.item.objects;

import org.bukkit.ChatColor;

public enum AbilityAction {
    LEFT_CLICK,
    RIGHT_CLICK,
    LEFT_SHIFT_CLICK,
    RIGHT_SHIFT_CLICK,
    FULL_SET,
    NONE,
    NULL;

    public boolean isLeftClick() {
        return this == LEFT_CLICK || this == LEFT_SHIFT_CLICK;
    }

    public boolean isRightClick() {
        return this == RIGHT_CLICK || this == RIGHT_SHIFT_CLICK;
    }

    public boolean isShiftClick() {
        return this == LEFT_SHIFT_CLICK || this == RIGHT_SHIFT_CLICK;
    }

    @Override
    public String toString() {
        if (this == AbilityAction.NONE) {
            return "";
        }
        return ChatColor.YELLOW + "" + ChatColor.BOLD + this.name().replace("_", " ");
    }
}
