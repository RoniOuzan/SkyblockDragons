package me.maxiiiiii.skyblockdragons.item.material.interfaces;

import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;

import java.util.List;

public interface ItemAbilityAble extends ItemAble {
    List<ItemAbility> getAbilities();
}
