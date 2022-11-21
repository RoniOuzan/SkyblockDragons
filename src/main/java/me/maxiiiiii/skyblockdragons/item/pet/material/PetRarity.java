package me.maxiiiiii.skyblockdragons.item.pet.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;

import java.util.Arrays;
import java.util.List;

@Getter
public class PetRarity {
    private final Rarity rarity;
    private final List<PetAbility> ability;

    public PetRarity(Rarity rarity, PetAbility... ability) {
        this.rarity = rarity;
        this.ability = Arrays.asList(ability);
    }
}
