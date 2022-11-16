package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;

import java.util.ArrayList;
import java.util.Arrays;

public class GatheringStats extends Stats {
    public GatheringStats(double miningSpeed,
                          double miningFortune,
                          double farmingFortune,
                          double foragingFortune
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.MINING_SPEED, miningSpeed),
                new Stat(StatTypes.MINING_FORTUNE, miningFortune),
                new Stat(StatTypes.FARMING_FORTUNE, farmingFortune),
                new Stat(StatTypes.FORAGING_FORTUNE, foragingFortune)
        )));
    }
}
