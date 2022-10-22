package me.maxiiiiii.skyblockdragons.item.objects;

import org.bukkit.ChatColor;

public enum AbilityAction {
    LEFT_CLICK("LEFT CLICK"),
    RIGHT_CLICK("RIGHT CLICK"),
    LEFT_SHIFT_CLICK("LEFT SHIFT CLICK"),
    RIGHT_SHIFT_CLICK("RIGHT SHIFT CLICK"),
    LEFT_NOT_SHIFT_CLICK("LEFT CLICK"),
    RIGHT_NOT_SHIFT_CLICK("RIGHT CLICK"),
    SHOOT(""), // TODO
    SNEAK("SNEAK"), // TODO
    NONE(""),
    NULL("NULL");

    private final String text;

    AbilityAction(String text) {
        this.text = text;
    }

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
        return ChatColor.YELLOW + "" + ChatColor.BOLD + this.text;
    }
}
