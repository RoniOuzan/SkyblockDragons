package me.maxiiiiii.skyblockdragons.item.pet.material;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@FunctionalInterface
public interface PetAbilitySupplier {
    String get(PlayerSD player, int level);
}
