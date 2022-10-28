package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityManaCost {
    double getBaseManaCost(PlayerSD player);

    default double getFinalCost(PlayerSD player) {
        if (player == null)
            return this.getBaseManaCost(null);

        return player.getAbilityCost(this.getBaseManaCost(player));
    }

    default boolean get(PlayerSD player) {
        boolean output = player.getStats().getMana().get() >= this.getFinalCost(player);
        if (!output) {
            player.sendMessage(ChatColor.RED + "You don't have enough mana to use this ability!");
        }
        return output;
    }

    default void applyCost(PlayerSD player) {
        player.getStats().getMana().remove(this.getFinalCost(player));
    }

    static String getLine(ItemAbilityManaCost ability, PlayerSD player) {
        return ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.AQUA + ability.getFinalCost(player);
    }
}
