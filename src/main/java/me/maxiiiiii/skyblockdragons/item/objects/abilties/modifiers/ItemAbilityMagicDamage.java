package me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilityMagicDamage {
    double getBaseAbilityDamage(PlayerSD player);
    default double getBaseAbilityScaling(PlayerSD player) {
        return 0;
    }

    default double getFinalAbilityDamage(PlayerSD player) {
        return player.getStats().getAbilityDamage().get() + player.getItemAbilityDamage(this.getBaseAbilityDamage(player));
    }

    default double getFinalAbilityScaling(PlayerSD player) {
        return player.getStats().getAbilityScaling().get() + player.getItemAbilityScaling(this.getBaseAbilityScaling(player));
    }
}
