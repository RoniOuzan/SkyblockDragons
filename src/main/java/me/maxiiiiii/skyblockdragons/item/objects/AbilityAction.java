package me.maxiiiiii.skyblockdragons.item.objects;

import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Function;

public enum AbilityAction {
    LEFT_CLICK("LEFT CLICK", e -> e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK),
    RIGHT_CLICK("RIGHT CLICK", e -> e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK),
    LEFT_SHIFT_CLICK("LEFT SHIFT CLICK", e -> (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && e.getPlayer().isSneaking()),
    RIGHT_SHIFT_CLICK("RIGHT SHIFT CLICK", e -> (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().isSneaking()),
    SHOOT(""), // TODO
    SNEAK("SNEAK"), // TODO
    PET(""),
    NONE(""),
    NULL("NULL");

    private final String text;
    private final Function<PlayerInteractEvent, Boolean> isPlayerUsedAbility;

    AbilityAction(String text, Function<PlayerInteractEvent, Boolean> isPlayerUsedAbility) {
        this.text = text;
        this.isPlayerUsedAbility = isPlayerUsedAbility;
    }

    AbilityAction(String text) {
        this(text, p -> false);
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

    public boolean isPlayerUsedAbility(PlayerInteractEvent event) {
        return isPlayerUsedAbility.apply(event);
    }

    @Override
    public String toString() {
        return ChatColor.YELLOW + "" + ChatColor.BOLD + this.text;
    }
}
