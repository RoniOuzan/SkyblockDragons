package me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.costs;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface ItemAbilityManaCostPercentage extends ItemAbilityManaCost {
    double getBaseManaCostPercentage(PlayerSD player);

    @Override
    default double getBaseManaCost(PlayerSD player) {
        return player.getStats().getIntelligence().get() * (getBaseManaCostPercentage(player) / 100);
    }

    static String getLine(ItemAbilityManaCostPercentage ability, PlayerSD player) {
        return ItemAbilityManaCost.getLine(ability, player) + "%";
    }
}
