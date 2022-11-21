package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;

import java.util.ArrayList;
import java.util.Arrays;

public class MiscStats extends Stats {
    public MiscStats(double magicFind,
                     double petLuck,
                     double seaCreatureChance,
                     double fishingSpeed
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.MAGIC_FIND, magicFind),
                new Stat(StatTypes.PET_LUCK, petLuck),
                new Stat(StatTypes.SEA_CREATURE_CHANCE, seaCreatureChance),
                new Stat(StatTypes.FISHING_SPEED, fishingSpeed)
        )));
    }
}
