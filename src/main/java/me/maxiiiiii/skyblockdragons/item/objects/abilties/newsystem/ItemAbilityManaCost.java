package me.maxiiiiii.skyblockdragons.item.objects.abilties.newsystem;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public abstract class ItemAbilityManaCost extends ItemAbilityNew {
    // TODO: make it cost mana

    protected ItemAbilityManaCost(AbilityAction action, String name, String description) {
        super(action, name, description);
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return null;
    }

    @Override
    public boolean isPlayerCanUseAbility(PlayerSD player) {
        return false;
    }
}
