package me.maxiiiiii.skyblockdragons.player;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;

import java.util.HashMap;
import java.util.Map;

public class StatsMultiplayer {
    private final Map<StatType, Double> stats;

    public StatsMultiplayer() {
        super();
        stats = new HashMap<>();
        for (StatType statType : StatType.values()) {
            stats.put(statType, 0d);
        }
    }

    public void increase(StatType statType, double amount) {
        if (amount != 0) stats.put(statType, stats.get(statType) + amount);
    }

    public void increase(double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double seaCreatureChance, double absorption) {
        this.increase(StatType.DAMAGE, damage);
        this.increase(StatType.STRENGTH, strength);
        this.increase(StatType.CRIT_DAMAGE, critDamage);
        this.increase(StatType.CRIT_CHANCE, critChance);
        this.increase(StatType.ABILITY_DAMAGE, abilityDamage);
        this.increase(StatType.ABILITY_SCALING, abilityScaling);
        this.increase(StatType.ATTACK_SPEED, attackSpeed);
        this.increase(StatType.FEROCITY, ferocity);
        this.increase(StatType.HEALTH, health);
        this.increase(StatType.DEFENSE, defense);
        this.increase(StatType.TRUE_DEFENSE, trueDefense);
        this.increase(StatType.SPEED, speed);
        this.increase(StatType.INTELLIGENCE, intelligence);
        this.increase(StatType.MAGIC_FIND, magicFind);
        this.increase(StatType.PET_LUCK, petLuck);
        this.increase(StatType.MINING_SPEED, miningSpeed);
        this.increase(StatType.MINING_FORTUNE, miningFortune);
        this.increase(StatType.SEA_CREATURE_CHANCE, seaCreatureChance);
        this.increase(StatType.ABSORPTION, absorption);
    }

    public void increase(double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence) {
        this.increase(damage, strength, critDamage, critChance, abilityDamage, abilityScaling, attackSpeed, ferocity, health, defense, trueDefense, speed, intelligence, 0, 0, 0, 0, 0, 0);
    }

    public void increase(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity) {
        this.increase(damage, strength, critDamage, critChance, 0, 0, attackSpeed, ferocity, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public void apply(PlayerStats playerStats) {
        for (StatType statType : this.stats.keySet()) {
            playerStats.get(statType).amount *= 1 + (this.stats.get(statType)) / 100d;
        }
    }
}
