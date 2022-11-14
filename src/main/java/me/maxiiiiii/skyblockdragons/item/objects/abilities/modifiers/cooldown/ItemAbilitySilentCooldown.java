package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;

public interface ItemAbilitySilentCooldown {
    double getBaseCooldown(PlayerSD player);

    default boolean get(PlayerSD player, Item item, ItemAbilityPerPlayer ability) {
        return ability.isCooldownExpired(this.getFinalCooldown(player, item));
    }

    default double getFinalCooldown(PlayerSD player, Item item) {
        UpdateCooldownEvent event = new UpdateCooldownEvent(player, item, this);
        Bukkit.getPluginManager().callEvent(event);

        return Math.round(event.getMultiplier().multiply(this.getBaseCooldown(player)) * 10) / 10d;
    }

    default void applyCost(ItemAbilityPerPlayer ability) {
        ability.resetCooldown();
    }
}
