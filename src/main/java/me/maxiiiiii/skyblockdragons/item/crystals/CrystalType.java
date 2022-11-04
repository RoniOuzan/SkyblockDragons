package me.maxiiiiii.skyblockdragons.item.crystals;

import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CrystalType {
    ATHENA("Athena", 3, StatType.INTELLIGENCE, 5.0, 10.0, 20.0),
    PHANES("Phanes", 3, StatType.HEALTH, 5.0, 10.0, 20.0),
    HERMES("Hermes", 3, StatType.SPEED, 5.0, 10.0, 20.0),
    DENETER("Demeter", 3, StatType.CRIT_DAMAGE, 5.0, 10.0, 20.0),
    ARES("Ares", 3, StatType.STRENGTH, 5.0, 10.0, 20.0);

    private final String name;
    private final int maxLevel;
    private final StatType stat;
    private final List<Double> amount;

    CrystalType(String name, int maxLevel, StatType stat, List<Double> amount) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.stat = stat;
        this.amount = amount;
    }

    CrystalType(String material, int maxLevel, StatType stat, Double... amount) {
        this(material, maxLevel, stat, Arrays.stream(amount).collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public ItemMaterial getMaterial(int level) {
        return Items.get(this.name.toUpperCase() + "_CRYSTAL_LEVEL_" + level);
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public Stat getStat(int level) {
        if (level > this.maxLevel) return new Stat(0, StatType.DAMAGE);

        return new Stat(amount.get(level - 1), stat);
    }
}
