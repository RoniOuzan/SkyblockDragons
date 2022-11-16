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

    public Stats(double damage, double strength, double critDamage, double critChance, double abilityDamage, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double farmingFortune, double foragingFortune, double seaCreatureChance) {
        this.damage = new Stat(damage, StatType.DAMAGE);
        this.strength = new Stat(strength, StatType.STRENGTH);
        this.critDamage = new Stat(critDamage, StatType.CRIT_DAMAGE);
        this.critChance = new Stat(critChance, StatType.CRIT_CHANCE);
        this.abilityDamage = new Stat(abilityDamage, StatType.ABILITY_DAMAGE);
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
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        this(damage, strength, critDamage, critChance, 0, attackSpeed, ferocity, health, defense, 0, speed, intelligence, 0, 0, 0, 0, 0, 0, 0);
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity) {
        this(damage, strength, critDamage, critChance, 0, attackSpeed, ferocity, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double miningSpeed, double miningFortune) {
        this(damage, strength, critDamage, critChance, 0, attackSpeed, ferocity, 0, 0, 0, 0, 0, 0, 0, miningSpeed, miningFortune, 0, 0, 0);
    }

    public Stats(double health, double defense, double trueDefense, double intelligence, double miningSpeed, double miningFortune, double magicFind) {
        this(0, 0, 0, 0, 0, 0, 0, health, defense, trueDefense, 0, intelligence, magicFind, 0, miningSpeed, miningFortune, 0, 0, 0);
    }

    public Stats(double health, double defense, double speed, double intelligence) {
        this(0, 0, 0, 0, 0, 0, health, defense, speed, intelligence);
    }

    public Stats(double miningSpeed, double miningFortune) {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, miningSpeed, miningFortune, 0, 0, 0);
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
                stats.seaCreatureChance.get()
        );
    }

    public Stats() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public List<Stat> toList() {
        return new ArrayList<>(Arrays.asList(
                damage,
                strength,
                critDamage,
                critChance,
                abilityDamage,
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
                seaCreatureChance
        ));
    }

    public void add(StatType stat, double amount) {
        this.get(stat).amount += amount;
    }

    public void multiply(double multiplier) {
        for (Stat stat : this) {
            stat.multiply(multiplier);
        }
    }

    public void reset() {
        this.damage.amount = 0;
        this.strength.amount = 0;
        this.critDamage.amount = 0;
        this.critChance.amount = 10;
        this.abilityDamage.amount = 0;
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
    }

    public void add(Stats stats) {
        this.add(stats.stream().map(Stat::get).collect(Collectors.toList()));
    }

    public void add(List<Double> num) {
        List<Stat> stats = this.toList();
        for (int i = 0; i < stats.size(); i++) {
            Stat stat = stats.get(i);
            if (i >= num.size())
                return;
            stat.add(num.get(i));
        }
    }

    public void normalize() {
        for (Stat stat : this) {
            stat.normalize();
        }
    }

    public Stat get(Stat stat) {
        return this.get(stat.type);
    }

    public Stat get(StatType stat) {
        for (Stat value : this) {
            if (value.type == stat) {
                return value;
            }
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
