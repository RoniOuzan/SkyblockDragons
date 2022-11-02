package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.UpdateManaCostEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;

public interface ItemAbilitySilentCooldown {
    double getBaseCooldown(PlayerSD player);

    default boolean get(PlayerSD player, ItemAbilityPerPlayer ability) {
        return ability.isCooldownExpired(this.getFinalCooldown(player));
    }

    default double getFinalCooldown(PlayerSD player) {
        UpdateCooldownEvent event = new UpdateCooldownEvent(player);
        Bukkit.getPluginManager().callEvent(event);

        return event.getMultiplier().multiply(this.getBaseCooldown(player));
    }

    default void applyCost(ItemAbilityPerPlayer ability) {
        ability.resetCooldown();
    }
}
