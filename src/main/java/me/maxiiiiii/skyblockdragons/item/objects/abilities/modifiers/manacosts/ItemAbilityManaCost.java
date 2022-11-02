package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public interface ItemAbilityManaCost {
    double getBaseManaCost(PlayerSD player);

    default double getFinalCost(PlayerSD player) {
        UpdateManaCostEvent event = new UpdateManaCostEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);

        return event.getMultiplier().multiply(this.getBaseManaCost(player));
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
