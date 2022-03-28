package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Stat;

import java.util.Arrays;

@Getter
public class SkillRewards {
    private final String name;
    private final int level;
    private final String passive;
    private final Stat stat;
    private final double[] statAmount;
    private final int[] coinsAmount;

    public SkillRewards(String name, int level, String passive, Stat stat, double[] statAmount, int[] coinsAmount) {
        this.name = name;
        this.level = level;
        this.passive = passive;
        this.stat = stat;
        this.statAmount = statAmount;
        this.coinsAmount = coinsAmount;
    }

    public SkillRewards(String name, int level, String passive, Stat stat, double statAmount, int[] coinsAmount) {
        this(name, level, passive, stat, convertFormat(statAmount), coinsAmount);
    }

    private static double[] convertFormat(double statAmount) {
        double[] statsAmount = new double[50];
        Arrays.fill(statsAmount, statAmount);
        return statsAmount;
    }

    public double getStatAmount() {
        return this.statAmount[this.level];
    }

    public int getCoinsAmount() {
        return this.coinsAmount[this.level];
    }
}
