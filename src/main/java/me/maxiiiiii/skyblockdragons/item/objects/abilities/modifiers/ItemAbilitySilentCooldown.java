package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilitySilentCooldown {
    double getBaseCooldown(PlayerSD player);

    default boolean get(PlayerSD player, ItemAbilityPerPlayer ability) {
        boolean output = ability.isCooldownExpired(this.getFinalCooldown(player));
        if (output) {
            ability.resetCooldown();
        }
        return output;
    }

    default double getFinalCooldown(PlayerSD player) {
        if (player == null) {
            return this.getBaseCooldown(null);
        }

        return player.getItemAbilityCooldown(this.getBaseCooldown(player));
    }
}
