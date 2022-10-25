package me.maxiiiiii.skyblockdragons.item.objects.pet;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;

import java.util.Arrays;
import java.util.List;

@Getter
public class PetRarity {
    private final Rarity rarity;
    private final List<PetAbilityNew> ability;

    public PetRarity(Rarity rarity, PetAbilityNew... ability) {
        this.rarity = rarity;
        this.ability = Arrays.asList(ability);
    }
}
