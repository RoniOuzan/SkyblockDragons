package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public abstract class ItemAbilityPercentCost extends ItemAbility {
    public ItemAbilityPercentCost(AbilityAction action, String name, String description, int manaCost, int cooldown, double abilityDamage, double abilityScaling) {
        super(action, name, description, manaCost, cooldown, abilityDamage, abilityScaling);
    }

    public ItemAbilityPercentCost(AbilityAction action, String name, String description, int manaCost, int cooldown) {
        super(action, name, description, manaCost, cooldown);
    }

    public ItemAbilityPercentCost(AbilityAction action, String name, String description) {
        super(action, name, description);
    }

    /**
     * @return the amount of percentage of the mana it will cost, 100 is 100%
     */
    protected abstract double getPercentageOfMana();

    @Override
    public boolean isPlayerHasEnoughMana(PlayerSD player) {
        return player.hasEnoughMana(player.getStats().getIntelligence().get() * (getPercentageOfMana() / 100));
    }
}
