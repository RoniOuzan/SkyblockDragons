package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public abstract class ItemAbilityManaCostPercentage extends ItemAbility {
    private final double percentageCost;

    protected ItemAbilityManaCostPercentage(AbilityAction action, String name, String description, double percentageCost) {
        super(action, name, description);
        this.percentageCost = percentageCost;
    }

    @Override
    public boolean isPlayerCanUseAbility(PlayerSD player) {
        return player.getStats().getMana().get() >= player.getAbilityCost(player.getStats().getIntelligence().get() * (percentageCost / 100));
    }
}
