package me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.costs;

import me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.ItemAbilityLoreModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityManaCost extends ItemAbilityLoreModifier {
    double getBaseManaCost(PlayerSD player);

    default double getCost(PlayerSD player) {
        return player.getAbilityCost(this.getBaseManaCost(player));
    }

    default boolean get(PlayerSD player) {
        return player.getStats().getMana().get() >= this.getCost(player);
    }

    default void applyCost(PlayerSD player) {
        player.getStats().getMana().remove(this.getCost(player));
    }

    static String getLine(ItemAbilityManaCost ability, PlayerSD player) {
        return ChatColor.GRAY + "Mana Cost: " + ChatColor.AQUA + ability.getCost(player);
    }
}
