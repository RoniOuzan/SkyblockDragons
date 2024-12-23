package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;

import java.util.ArrayList;
import java.util.Arrays;

public class DamageStats extends Stats {
    public DamageStats(double damage,
                       double strength,
                       double critDamage,
                       double critChance,
                       double abilityDamage,
                       double attackSpeed,
                       double ferocity
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.DAMAGE, damage),
                new Stat(StatTypes.STRENGTH, strength),
                new Stat(StatTypes.CRIT_DAMAGE, critDamage),
                new Stat(StatTypes.CRIT_CHANCE, critChance),
                new Stat(StatTypes.ABILITY_DAMAGE, abilityDamage),
                new Stat(StatTypes.ATTACK_SPEED, attackSpeed),
                new Stat(StatTypes.FEROCITY, ferocity)
        )));
    }

    public DamageStats(double damage,
                       double strength,
                       double critDamage,
                       double critChance,
                       double attackSpeed,
                       double ferocity
    ) {
        this(damage, strength, critDamage, critChance, 0, attackSpeed, ferocity);
    }

    public DamageStats(double damage,
                       double strength,
                       double critDamage,
                       double critChance,
                       double attackSpeed,
                       double ferocity,
                       double miningSpeed,
                       double miningFortune) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.DAMAGE, damage),
                new Stat(StatTypes.STRENGTH, strength),
                new Stat(StatTypes.CRIT_DAMAGE, critDamage),
                new Stat(StatTypes.CRIT_CHANCE, critChance),
                new Stat(StatTypes.ATTACK_SPEED, attackSpeed),
                new Stat(StatTypes.FEROCITY, ferocity),
                new Stat(StatTypes.MINING_SPEED, miningSpeed),
                new Stat(StatTypes.MINING_FORTUNE, miningFortune)
        )));
    }
}
