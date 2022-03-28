package me.maxiiiiii.skyblockdragons.player.pet;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;

import java.util.ArrayList;

@Getter
@Setter
public class PetAbility {
    private String name;
    private String description;
    private String other;
    private ArrayList<Rarity> rarities;

    public PetAbility(String name, String description, String other, ArrayList<Rarity> rarities) {
        this.name = name;
        this.description = description;
        this.other = other;
        this.rarities = rarities;
    }

    public PetAbility(String name, String description, ArrayList<Rarity> rarities) {
        this(name, description, "", rarities);
    }
}
