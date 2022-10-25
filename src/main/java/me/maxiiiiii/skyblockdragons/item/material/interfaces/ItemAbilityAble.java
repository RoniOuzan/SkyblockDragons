package me.maxiiiiii.skyblockdragons.item.material.interfaces;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;

import java.util.List;

public interface ItemAbilityAble extends ItemAble {
    List<ItemAbility> getAbilities();

    default ItemFullSetBonus getFullSetBonus() {
        return this.getAbilities().stream()
                .filter(a -> a instanceof ItemFullSetBonus)
                .map(a -> (ItemFullSetBonus) a)
                .findFirst().orElse(null);
    }
}
