package me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilitySilentCooldown {
    double getBaseCooldown(PlayerSD player);

    default double getFinalCooldown(PlayerSD player) {
        return player.getItemAbilityCooldown(this.getBaseCooldown(player));
    }
}
