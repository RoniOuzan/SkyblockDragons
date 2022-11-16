package me.maxiiiiii.skyblockdragons.item.stats.constructors;

import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;

import java.util.ArrayList;
import java.util.Arrays;

public class CombatStats extends Stats {
    public CombatStats(double health,
                       double defense,
                       double trueDefense,
                       double speed,
                       double vitality,
                       double intelligence,
                       double damage,
                       double strength,
                       double critDamage,
                       double critChance,
                       double abilityDamage,
                       double attackSpeed,
                       double ferocity
    ) {
        super(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.TRUE_DEFENSE, trueDefense),
                new Stat(StatTypes.SPEED, speed),
                new Stat(StatTypes.VITALITY, vitality),
                new Stat(StatTypes.INTELLIGENCE, intelligence),
                new Stat(StatTypes.DAMAGE, damage),
                new Stat(StatTypes.STRENGTH, strength),
                new Stat(StatTypes.CRIT_DAMAGE, critDamage),
                new Stat(StatTypes.CRIT_CHANCE, critChance),
                new Stat(StatTypes.ABILITY_DAMAGE, abilityDamage),
                new Stat(StatTypes.ATTACK_SPEED, attackSpeed),
                new Stat(StatTypes.FEROCITY, ferocity)
        )));
    }
}
