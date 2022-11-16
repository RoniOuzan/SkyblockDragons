package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;

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
}
