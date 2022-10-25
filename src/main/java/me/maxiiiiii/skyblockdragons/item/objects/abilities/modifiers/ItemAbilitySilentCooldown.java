package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilitySilentCooldown {
    double getBaseCooldown(PlayerSD player);

    default double getFinalCooldown(PlayerSD player) {
        if (player == null) {
            return this.getBaseCooldown(null);
        }

        return player.getItemAbilityCooldown(this.getBaseCooldown(player));
    }
}
