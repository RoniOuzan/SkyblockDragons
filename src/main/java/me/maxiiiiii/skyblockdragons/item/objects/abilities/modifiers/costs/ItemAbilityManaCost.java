package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityManaCost {
    double getBaseManaCost(PlayerSD player);

    default double getFinalCost(PlayerSD player) {
        return player.getAbilityCost(this.getBaseManaCost(player));
    }

    default boolean get(PlayerSD player) {
        return player.getStats().getMana().get() >= this.getFinalCost(player);
    }

    default void applyCost(PlayerSD player) {
        player.getStats().getMana().remove(this.getFinalCost(player));
    }

    static String getLine(ItemAbilityManaCost ability, PlayerSD player) {
        return ChatColor.GRAY + "Mana Cost: " + ChatColor.AQUA + ability.getFinalCost(player);
    }
}
