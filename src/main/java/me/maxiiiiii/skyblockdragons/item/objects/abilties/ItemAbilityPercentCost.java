package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public abstract class ItemAbilityPercentCost extends ItemAbility {
    public ItemAbilityPercentCost(AbilityAction action, String name, String description, int manaCost, boolean customManaCost, int cooldown, double abilityDamage, double abilityScaling) {
        super(action, name, description, manaCost, customManaCost, cooldown, abilityDamage, abilityScaling);
    }

    public ItemAbilityPercentCost(AbilityAction action, String name, String description, int manaCost, boolean customManaCost, int cooldown) {
        super(action, name, description, manaCost, customManaCost, cooldown);
    }

    public ItemAbilityPercentCost(AbilityAction action, String name, String description) {
        super(action, name, description);
    }

    public ItemAbilityPercentCost(AbilityAction action, String name, String description, boolean customManaCost) {
        super(action, name, description, customManaCost);
    }

    @Override
    public int getManaCost(PlayerSD player) {
        return (int) (player.getStats().getIntelligence().get() / super.getManaCost());
    }
}
