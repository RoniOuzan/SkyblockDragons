package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;

import java.util.ArrayList;
import java.util.Arrays;

public class SurvivorStats extends Stats {
    public SurvivorStats(double health,
                         double defense,
                         double trueDefense,
                         double speed,
                         double vitality,
                         double intelligence
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.TRUE_DEFENSE, trueDefense),
                new Stat(StatTypes.SPEED, speed),
                new Stat(StatTypes.VITALITY, vitality),
                new Stat(StatTypes.INTELLIGENCE, intelligence)
        )));
    }

    public SurvivorStats(double health,
                         double defense,
                         double trueDefense,
                         double speed,
                         double vitality,
                         double intelligence,
                         double miningSpeed,
                         double miningFortune,
                         double farmingFortune,
                         double foragingFortune
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.TRUE_DEFENSE, trueDefense),
                new Stat(StatTypes.SPEED, speed),
                new Stat(StatTypes.VITALITY, vitality),
                new Stat(StatTypes.INTELLIGENCE, intelligence),
                new Stat(StatTypes.MINING_SPEED, miningSpeed),
                new Stat(StatTypes.MINING_FORTUNE, miningFortune),
                new Stat(StatTypes.FARMING_FORTUNE, farmingFortune),
                new Stat(StatTypes.FORAGING_FORTUNE, foragingFortune)
        )));
    }

    public SurvivorStats(double health,
                         double defense,
                         double trueDefense,
                         double intelligence,
                         double magicFind,
                         double miningSpeed,
                         double miningFortune
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.TRUE_DEFENSE, trueDefense),
                new Stat(StatTypes.INTELLIGENCE, intelligence),
                new Stat(StatTypes.MAGIC_FIND, magicFind),
                new Stat(StatTypes.MINING_SPEED, miningSpeed),
                new Stat(StatTypes.MINING_FORTUNE, miningFortune)
        )));
    }
}
