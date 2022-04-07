package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class SkillRewards implements ConfigurationSerializable {
    private final String name;
    private final int level;
    private final String passive;
    private final StatType stat;
    private final double[] statAmount;
    private final int[] coinsAmount;

    public SkillRewards(String name, int level, String passive, StatType stat, double[] statAmount, int[] coinsAmount) {
        this.name = name;
        this.level = level;
        this.passive = passive;
        this.stat = stat;
        this.statAmount = statAmount;
        this.coinsAmount = coinsAmount;
    }

    public SkillRewards(String name, int level, String passive, StatType stat, double statAmount, int[] coinsAmount) {
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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("level", level);
        args.put("passive", passive);
        args.put("stat", stat);
        args.put("statAmount", statAmount);
        args.put("coinsAmount", coinsAmount);
        return args;
    }

    public static SkillRewards deserialize(Map<String, Object> args) {
        return new SkillRewards((String) args.get("name"), (int) args.get("level"), (String) args.get("passive"), (StatType) args.get("stat"), (double[]) args.get("statAmount"), (int[]) args.get("coinsAmount"));
    }
}
