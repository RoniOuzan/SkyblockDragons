package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;

public interface ItemAbilityManaCost {
    double getBaseManaCost(PlayerSD player);

    default double getFinalCost(PlayerSD player, Item item) {
        UpdateManaCostEvent event = new UpdateManaCostEvent(player, this, item);
        Bukkit.getPluginManager().callEvent(event);

        return event.getMultiplier().multiply(this.getBaseManaCost(player));
    }

    default boolean get(PlayerSD player, Item item) {
        boolean output = player.getStats().getMana().get() >= this.getFinalCost(player, item);
        if (!output) {
            player.sendMessage(ChatColor.RED + "You don't have enough mana to use this ability!");
        }
        return output;
    }

    default void applyCost(PlayerSD player, Item item) {
        if (player.getGameMode() == GameMode.CREATIVE) return;

        player.getStats().getMana().remove(this.getFinalCost(player, item));
    }

    static String getLine(ItemAbilityManaCost ability, PlayerSD player, Item item) {
        return ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.AQUA + ability.getFinalCost(player, item);
    }
}
