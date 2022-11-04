package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Getter
public class Stats implements Iterable<Stat> {
    protected final Stat damage;
    protected final Stat strength;
    protected final Stat critDamage;
    protected final Stat critChance;
    protected final Stat abilityDamage;
    protected final Stat abilityScaling;
    protected final Stat attackSpeed;
    protected final Stat ferocity;
    protected final Stat health;
    protected final Stat defense;
    protected final Stat trueDefense;
    protected final Stat speed;
    protected final Stat intelligence;
    protected final Stat magicFind;
    protected final Stat petLuck;
    protected final Stat miningSpeed;
    protected final Stat miningFortune;
    protected final Stat farmingFortune;
    protected final Stat foragingFortune;
    protected final Stat seaCreatureChance;
    protected final Stat absorption;

    public Stats(double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double farmingFortune, double foragingFortune, double seaCreatureChance, double absorption) {
        this.damage = new Stat(damage, StatType.DAMAGE);
        this.strength = new Stat(strength, StatType.STRENGTH);
        this.critDamage = new Stat(critDamage, StatType.CRIT_DAMAGE);
        this.critChance = new Stat(critChance, StatType.CRIT_CHANCE);
        this.abilityDamage = new Stat(abilityDamage, StatType.ABILITY_DAMAGE);
        this.abilityScaling = new Stat(abilityScaling, StatType.ABILITY_SCALING);
        this.attackSpeed = new Stat(attackSpeed, StatType.ATTACK_SPEED);
        this.ferocity = new Stat(ferocity, StatType.FEROCITY);
        this.health = new Stat(health, StatType.HEALTH);
        this.defense = new Stat(defense, StatType.DEFENSE);
        this.trueDefense = new Stat(trueDefense, StatType.TRUE_DEFENSE);
        this.speed = new Stat(speed, StatType.SPEED);
        this.intelligence = new Stat(intelligence, StatType.INTELLIGENCE);
        this.magicFind = new Stat(magicFind, StatType.MAGIC_FIND);
        this.petLuck = new Stat(petLuck, StatType.PET_LUCK);
        this.miningSpeed = new Stat(miningSpeed, StatType.MINING_SPEED);
        this.miningFortune = new Stat(miningFortune, StatType.MINING_FORTUNE);
        this.farmingFortune = new Stat(farmingFortune, StatType.FARMING_FORTUNE);
        this.foragingFortune = new Stat(foragingFortune, StatType.FORAGING_FORTUNE);
        this.seaCreatureChance = new Stat(seaCreatureChance, StatType.SEA_CREATURE_CHANCE);
        this.absorption = new Stat(absorption, StatType.ABSORPTION);
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        this(damage, strength, critDamage, critChance, 0, 0, attackSpeed, ferocity, health, defense, 0, speed, intelligence, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity) {
        this(damage, strength, critDamage, critChance, 0, 0, attackSpeed, ferocity, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double miningSpeed, double miningFortune) {
        this(damage, strength, critDamage, critChance, 0, 0, attackSpeed, ferocity, 0, 0, 0, 0, 0, 0, 0, miningSpeed, miningFortune, 0, 0, 0, 0);
    }

    public Stats(double health, double defense, double trueDefense, double intelligence, double miningSpeed, double miningFortune, double magicFind) {
        this(0, 0, 0, 0, 0, 0, 0, 0, health, defense, trueDefense, 0, intelligence, magicFind, 0, miningSpeed, miningFortune, 0, 0, 0, 0);
    }

    public Stats(double health, double defense, double speed, double intelligence) {
        this(0, 0, 0, 0, 0, 0, health, defense, speed, intelligence);
    }

    public Stats(double miningSpeed, double miningFortune) {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, miningSpeed, miningFortune, 0, 0, 0, 0);
    }

    public Stats(StatType stat, double amount) {
        this();
        this.add(stat, amount);
    }

    public Stats(Object... statsAndAmounts) {
        this();
        List<StatType> stats = Arrays.stream(statsAndAmounts).filter(o -> o instanceof StatType).map(o -> (StatType) o).collect(Collectors.toList());
        List<Double> amounts = Arrays.stream(statsAndAmounts).filter(o -> o instanceof Double).map(o -> (Double) o).collect(Collectors.toList());
        for (int i = 0; i < stats.size(); i++) {
            this.add(stats.get(i), amounts.get(i));
        }
    }

    public Stats(Stats stats) {
        this(
                stats.damage.get(),
                stats.strength.get(),
                stats.critDamage.get(),
                stats.critChance.get(),
                stats.abilityDamage.get(),
                stats.abilityScaling.get(),
                stats.attackSpeed.get(),
                stats.ferocity.get(),
                stats.health.get(),
                stats.defense.get(),
                stats.trueDefense.get(),
                stats.speed.get(),
                stats.intelligence.get(),
                stats.magicFind.get(),
                stats.petLuck.get(),
                stats.miningSpeed.get(),
                stats.miningFortune.get(),
                stats.farmingFortune.get(),
                stats.foragingFortune.get(),
                stats.seaCreatureChance.get(),
                stats.absorption.get()
        );
    }

    public Stats() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public List<Stat> toList() {
        return new ArrayList<>(Arrays.asList(
                damage,
                strength,
                critDamage,
                critChance,
                abilityDamage,
                abilityScaling,
                attackSpeed,
                ferocity,
                health,
                defense,
                trueDefense,
                speed,
                intelligence,
                magicFind,
                petLuck,
                miningSpeed,
                miningFortune,
                farmingFortune,
                foragingFortune,
                seaCreatureChance,
                absorption
        ));
    }

    public Stat getStat(StatType stat) {
        switch (stat) {
            case DAMAGE:
                return this.damage;
            case STRENGTH:
                return this.strength;
            case CRIT_DAMAGE:
                return this.critDamage;
            case CRIT_CHANCE:
                return this.critChance;
            case ABILITY_DAMAGE:
                return this.abilityDamage;
            case ABILITY_SCALING:
                return this.abilityScaling;
            case ATTACK_SPEED:
                return this.attackSpeed;
            case FEROCITY:
                return this.ferocity;
            case HEALTH:
                return this.health;
            case DEFENSE:
                return this.defense;
            case TRUE_DEFENSE:
                return this.trueDefense;
            case SPEED:
                return this.speed;
            case INTELLIGENCE:
                return this.intelligence;
            case MAGIC_FIND:
                return this.magicFind;
            case PET_LUCK:
                return this.petLuck;
            case MINING_SPEED:
                return this.miningSpeed;
            case MINING_FORTUNE:
                return this.miningFortune;
            case SEA_CREATURE_CHANCE:
                return this.seaCreatureChance;
            case ABSORPTION:
                return this.absorption;
        }
        return null;
    }

    public void add(StatType stat, double amount) {
        this.get(stat).amount += amount;
    }

    public void increasePlayerStat(double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double seaCreatureChance, double absorption) {
        this.damage.increase(damage);
        this.strength.increase(strength);
        this.critDamage.increase(critDamage);
        this.critChance.increase(critChance);
        this.abilityDamage.increase(abilityDamage);
        this.abilityScaling.increase(abilityScaling);
        this.attackSpeed.increase(attackSpeed);
        this.ferocity.increase(ferocity);
        this.health.increase(health);
        this.defense.increase(defense);
        this.trueDefense.increase(trueDefense);
        this.speed.increase(speed);
        this.intelligence.increase(intelligence);
        this.magicFind.increase(magicFind);
        this.petLuck.increase(petLuck);
        this.miningSpeed.increase(miningSpeed);
        this.miningFortune.increase(miningFortune);
        this.seaCreatureChance.increase(seaCreatureChance);
        this.absorption.increase(absorption);
    }

    public void reset() {
        this.damage.amount = 0;
        this.strength.amount = 0;
        this.critDamage.amount = 0;
        this.critChance.amount = 10;
        this.abilityDamage.amount = 0;
        this.abilityScaling.amount = 1;
        this.attackSpeed.amount = 0;
        this.ferocity.amount = 0;
        this.health.amount = 100;
        this.defense.amount = 0;
        this.trueDefense.amount = 0;
        this.speed.amount = 100;
        this.intelligence.amount = 100;
        this.magicFind.amount = 0;
        this.petLuck.amount = 0;
        this.miningSpeed.amount = 0;
        this.miningFortune.amount = 0;
        this.seaCreatureChance.amount = 0;
        this.absorption.amount = 0;
    }

    public void add(Stats stats) {
        this.add(stats.stream().map(s -> s.amount).collect(Collectors.toList()));
    }

    public void add(List<Double> num) {
        try {
            this.damage.amount += num.get(0);
            this.strength.amount += num.get(1);
            this.critDamage.amount += num.get(2);
            this.critChance.amount += num.get(3);
            this.abilityDamage.amount += num.get(4);
            this.abilityScaling.amount += num.get(5);
            this.attackSpeed.amount += num.get(6);
            this.ferocity.amount += num.get(7);
            this.health.amount += num.get(8);
            this.defense.amount += num.get(9);
            this.trueDefense.amount += num.get(10);
            this.speed.amount += num.get(11);
            this.intelligence.amount += num.get(12);
            this.magicFind.amount += num.get(13);
            this.petLuck.amount += num.get(14);
            this.miningSpeed.amount += num.get(15);
            this.miningFortune.amount += num.get(16);
            this.seaCreatureChance.amount += num.get(17);
            this.absorption.amount += num.get(18);
        } catch (IndexOutOfBoundsException ignored) {}
    }

    public void normalize() {
        this.damage.normalize();
        this.strength.normalize();
        this.critDamage.normalize();
        this.critChance.normalize();
        this.abilityDamage.normalize();
        this.abilityScaling.normalize();
        this.attackSpeed.normalize();
        this.ferocity.normalize();
        this.health.normalize();
        this.defense.normalize();
        this.trueDefense.normalize();
        this.speed.normalize();
        this.intelligence.normalize();
        this.magicFind.normalize();
        this.petLuck.normalize();
        this.miningSpeed.normalize();
        this.miningFortune.normalize();
        this.seaCreatureChance.normalize();
        this.absorption.normalize();
    }

    public Stat get(Stat stat) {
        switch (stat.type) {
            case DAMAGE:
                return this.damage;
            case STRENGTH:
                return this.strength;
            case CRIT_DAMAGE:
                return this.critDamage;
            case CRIT_CHANCE:
                return this.critChance;
            case ABILITY_DAMAGE:
                return this.abilityDamage;
            case ABILITY_SCALING:
                return this.abilityScaling;
            case ATTACK_SPEED:
                return this.attackSpeed;
            case FEROCITY:
                return this.ferocity;
            case HEALTH:
                return this.health;
            case DEFENSE:
                return this.defense;
            case TRUE_DEFENSE:
                return this.trueDefense;
            case SPEED:
                return this.speed;
            case INTELLIGENCE:
                return this.intelligence;
            case MAGIC_FIND:
                return this.magicFind;
            case PET_LUCK:
                return this.petLuck;
            case MINING_SPEED:
                return this.miningSpeed;
            case MINING_FORTUNE:
                return this.miningFortune;
            case FARMING_FORTUNE:
                return this.farmingFortune;
            case FORAGING_FORTUNE:
                return this.foragingFortune;
            case SEA_CREATURE_CHANCE:
                return this.seaCreatureChance;
            case ABSORPTION:
                return this.absorption;
        }
        return null;
    }

    public Stat get(StatType stat) {
        switch (stat) {
            case DAMAGE:
                return this.damage;
            case STRENGTH:
                return this.strength;
            case CRIT_DAMAGE:
                return this.critDamage;
            case CRIT_CHANCE:
                return this.critChance;
            case ABILITY_DAMAGE:
                return this.abilityDamage;
            case ABILITY_SCALING:
                return this.abilityScaling;
            case ATTACK_SPEED:
                return this.attackSpeed;
            case FEROCITY:
                return this.ferocity;
            case HEALTH:
                return this.health;
            case DEFENSE:
                return this.defense;
            case TRUE_DEFENSE:
                return this.trueDefense;
            case SPEED:
                return this.speed;
            case INTELLIGENCE:
                return this.intelligence;
            case MAGIC_FIND:
                return this.magicFind;
            case PET_LUCK:
                return this.petLuck;
            case MINING_SPEED:
                return this.miningSpeed;
            case MINING_FORTUNE:
                return this.miningFortune;
            case FARMING_FORTUNE:
                return this.farmingFortune;
            case FORAGING_FORTUNE:
                return this.foragingFortune;
            case SEA_CREATURE_CHANCE:
                return this.seaCreatureChance;
            case ABSORPTION:
                return this.absorption;
        }
        return null;
    }

    @Override
    public Iterator<Stat> iterator() {
        return this.toList().iterator();
    }

    @Override
    public void forEach(Consumer<? super Stat> action) {
        Objects.requireNonNull(action);
        for (Stat e : this.toList()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Stat> spliterator() {
        return Spliterators.spliterator(this.toList(), Spliterator.ORDERED);
    }

    public Stream<Stat> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
