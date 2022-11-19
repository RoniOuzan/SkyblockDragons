package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;

@Getter
public class FilledStat extends Stat {
    private final Stat filler;

    public FilledStat(StatType type, double amount, StatType fillerType, double fillerAmount) {
        super(type, amount);
        this.filler = new Stat(fillerType, fillerAmount);
    }

    public FilledStat(double amount, StatType type, double fillerAmount, StatType fillerType) {
        this(type, amount, fillerType, fillerAmount);
    }

    public FilledStat(StatType type, StatType fillerType) {
        this(type, 0, fillerType, 0);
    }
}
