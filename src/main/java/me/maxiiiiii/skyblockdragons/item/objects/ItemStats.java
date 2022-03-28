package me.maxiiiiii.skyblockdragons.item.objects;

import java.util.ArrayList;

public class ItemStats extends ArrayList<Double> {
    public double damage;
    public double strength;
    public double critDamage;
    public double critChance;
    public double abilityDamage;
    public double baseAbilityDamage;
    public double abilityScaling;
    public double attackSpeed;
    public double ferocity;
    public double health;
    public double defense;
    public double trueDefense;
    public double speed;
    public double intelligence;
    public double magicFind;
    public double petLuck;
    public double miningSpeed;
    public double miningFortune;
    public double seaCreatureChance;
    public double absorption;

    public ItemStats(double damage, double strength, double critDamage, double critChance, double abilityDamage, double baseAbilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double seaCreatureChance, double absorption) {
        this.damage = damage;
        this.strength = strength;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.abilityDamage = abilityDamage;
        this.baseAbilityDamage = baseAbilityDamage;
        this.abilityScaling = abilityScaling;
        this.attackSpeed = attackSpeed;
        this.ferocity = ferocity;
        this.health = health;
        this.defense = defense;
        this.trueDefense = trueDefense;
        this.speed = speed;
        this.intelligence = intelligence;
        this.magicFind = magicFind;
        this.petLuck = petLuck;
        this.miningSpeed = miningSpeed;
        this.miningFortune = miningFortune;
        this.seaCreatureChance = seaCreatureChance;
        this.absorption = absorption;
    }

    public ItemStats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        this(damage, strength, critDamage, critChance, 0, 0, 0, attackSpeed, ferocity, health, defense, 0, speed, intelligence, 0, 0, 0, 0, 0, 0);
    }

    public ItemStats() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
