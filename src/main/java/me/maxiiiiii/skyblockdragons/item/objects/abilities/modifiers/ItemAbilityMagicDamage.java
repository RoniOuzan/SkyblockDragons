package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilityMagicDamage {
    double getBaseAbilityDamage(PlayerSD player);

    default double getBaseAbilityScaling(PlayerSD player) {
        return 0;
    }

    default double getFinalAbilityDamage(PlayerSD player) {
        return this.getBaseAbilityDamage(player) * (1 + ((player.getStats().getIntelligence().get() / 100) * this.getBaseAbilityScaling(player)));
    }
}
