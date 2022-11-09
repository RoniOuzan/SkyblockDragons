package me.maxiiiiii.skyblockdragons.item.crystals;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;

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

    public Stat getStat() {
        return new Stat(this.crystal.getAmount().get(this.level - 1), this.crystal.getStatType());
    }

    @Override
    public String toString() {
        return this.crystal.getName() + " " + this.level;
    }
}
