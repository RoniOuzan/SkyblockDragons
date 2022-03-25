package me.maxiiiiii.skyblockdragons.itemcreator.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemStats extends ArrayList<Double> {
    public double damage;
    public double strength;
    public double critDamage;
    public double critChance;
    public double abilityDamage;
    public double abilityScaling;
    public double attackSpeed;
    public double ferocity;
    public double health;
    public double defense;
    public double speed;
    public double intelligence;

    public ItemStats(double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        super(Arrays.asList(damage, strength, critDamage, critChance, abilityDamage, abilityScaling, attackSpeed, ferocity, health, defense, speed, intelligence));
        this.damage = damage;
        this.strength = strength;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.abilityDamage = abilityDamage;
        this.abilityScaling = abilityScaling;
        this.attackSpeed = attackSpeed;
        this.ferocity = ferocity;
        this.health = health;
        this.defense = defense;
        this.speed = speed;
        this.intelligence = intelligence;
    }

    public ItemStats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        this(damage, strength, critDamage, critChance, 0, 0, attackSpeed, ferocity, health, defense, speed, intelligence);
    }

    public ItemStats() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
