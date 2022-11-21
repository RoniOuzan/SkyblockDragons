package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;

import java.util.ArrayList;
import java.util.Arrays;

public class WisdomStats extends Stats {
    public WisdomStats(double farmingWisdom,
                       double miningWisdom,
                       double combatWisdom,
                       double foragingWisdom,
                       double fishingWisdom,
                       double enchantingWisdom,
                       double alchemyWisdom,
                       double tamingWisdom,
                       double dungeoneeringWisdom
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.FARMING_WISDOM, farmingWisdom),
                new Stat(StatTypes.MINING_WISDOM, miningWisdom),
                new Stat(StatTypes.COMBAT_WISDOM, combatWisdom),
                new Stat(StatTypes.FORAGING_WISDOM, foragingWisdom),
                new Stat(StatTypes.FISHING_WISDOM, fishingWisdom),
                new Stat(StatTypes.ENCHANTING_WISDOM, enchantingWisdom),
                new Stat(StatTypes.ALCHEMY_WISDOM, alchemyWisdom),
                new Stat(StatTypes.TAMING_WISDOM, tamingWisdom),
                new Stat(StatTypes.DUNGEONEERING_WISDOM, dungeoneeringWisdom)
        )));
    }
}
