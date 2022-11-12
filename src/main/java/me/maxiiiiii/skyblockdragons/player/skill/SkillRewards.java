package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;

import java.util.function.Function;

@Getter
public class SkillRewards {
    private final String name;
    @Getter(AccessLevel.NONE) private final Function<Integer, String> passive;
    private final StatType stat;
    private final double statAmount;
    private final int[] coinsAmount;

    public SkillRewards(String name, Function<Integer, String> passive, StatType stat, double statAmount, int[] coinsAmount) {
        this.name = name;
        this.passive = passive;
        this.stat = stat;
        this.statAmount = statAmount;
        this.coinsAmount = coinsAmount;
    }

    public SkillRewards(String name, String passive, StatType stat, double statAmount, int[] coinsAmount) {
        this(name, l -> passive, stat, statAmount, coinsAmount);
    }

    public int getCoins(int level) {
        if (level == 0) return 0;

        return this.coinsAmount[level - 1];
    }

    public String getPassive(int level) {
        return this.passive.apply(level);
    }
}
