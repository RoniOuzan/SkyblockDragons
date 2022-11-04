package me.maxiiiiii.skyblockdragons.item.crystals;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;

@Getter
public class Crystal {
    private final CrystalType crystal;
    private final int level;

    public Crystal(CrystalType crystal, int level) {
        this.crystal = crystal;
        this.level = level;
    }

    public Item getItem() {
        return new Item(crystal.getMaterial(level));
    }
}
