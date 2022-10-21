package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;

public class NullItemAbility extends ItemAbility {
    public NullItemAbility() {
        super(AbilityAction.NULL, "Null", "This item doesn't have any ability");
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {};
    }
}
