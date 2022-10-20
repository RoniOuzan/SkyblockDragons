package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public abstract class ItemAbilityManaCost extends ItemAbility {
    private final double baseManaCost;

    protected ItemAbilityManaCost(AbilityAction action, String name, String description, double manaCost) {
        super(action, name, description);
        this.baseManaCost = manaCost;
    }

    @Override
    public boolean isPlayerCanUseAbility(PlayerSD player) {
        return player.getStats().getMana().get() >= player.getAbilityCost(baseManaCost);
    }
}
