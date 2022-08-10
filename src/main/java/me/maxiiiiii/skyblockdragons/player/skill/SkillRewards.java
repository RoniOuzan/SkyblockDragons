package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class SkillRewards {
    private final String name;
    private final String passive;
    private final StatType stat;
    private final double statAmount;
    private final int[] coinsAmount;

    public SkillRewards(String name, String passive, StatType stat, double statAmount, int[] coinsAmount) {
        this.name = name;
        this.passive = passive;
        this.stat = stat;
        this.statAmount = statAmount;
        this.coinsAmount = coinsAmount;
    }

    public int getCoinsAmount(int level) {
        return this.coinsAmount[level];
    }
}
