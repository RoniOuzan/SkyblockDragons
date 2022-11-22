package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilityManaCostPercentage extends ItemAbilityManaCost {
    /**
     * @return 100 is 100%
     */
    double getBaseManaCostPercentage(PlayerSD player);

    @Override
    default double getBaseManaCost(PlayerSD player) {
        if (player == null) return getBaseManaCostPercentage(null);

        return player.getStats().getIntelligence().get() * (getBaseManaCostPercentage(player) / 100);
    }
}
