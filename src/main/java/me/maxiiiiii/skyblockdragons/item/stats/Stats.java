package me.maxiiiiii.skyblockdragons.item.stats;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.stats.stats.combat.ManaStat;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Stats implements Iterable<Stat> {
    private final Map<StatType, Stat> stats;

    public Stats(List<Stat> stats) {
        this.stats = new HashMap<>();
        for (Stat stat : stats) {
            this.stats.put(stat.getType(), new Stat(stat.getType(), stat.get()));
        }
    }

    public Stats() {
        this(new ArrayList<>());
    }

    protected double getDefaultValue(StatType type) {
        return 0;
    }

    private Stat getDefaultStat(StatType type) {
        if (type instanceof me.maxiiiiii.skyblockdragons.item.stats.interfaces.FilledStat)
            return new FilledStat(type, getDefaultValue(type), ((me.maxiiiiii.skyblockdragons.item.stats.interfaces.FilledStat) type).getFiller(), getDefaultValue(((me.maxiiiiii.skyblockdragons.item.stats.interfaces.FilledStat) type).getFiller()));
        return new Stat(type, getDefaultValue(type));
    }
    
    public Stat get(StatType type) {
        return stats.getOrDefault(type, getDefaultStat(type));
    }

    public Stats set(StatType type, double amount) {
        Stat stat = stats.getOrDefault(type, getDefaultStat(type));
        stat.set(amount);
        stats.put(type, stat);
        return this;
    }

    public void add(StatType type, double amount) {
        Stat stat = stats.getOrDefault(type, getDefaultStat(type));
        stat.add(amount);
        stats.put(type, stat);
    }

    public void add(Stats stats) {
        for (Stat stat : stats) {
            this.add(stat.getType(), stat.get());
        }
    }

    public void remove(StatType type, double amount) {
        Stat stat = stats.getOrDefault(type, getDefaultStat(type));
        stat.remove(amount);
        stats.put(type, stat);
    }

    public void multiply(StatType type, double multiplier) {
        Stat stat = stats.getOrDefault(type, getDefaultStat(type));
        stat.multiply(multiplier);
        stats.put(type, stat);
    }

    public void multiply(double multiplier) {
        for (Stat stat : this) {
            stat.multiply(multiplier);
        }
    }

    public void normalize(PlayerSD player, StatType type) {
        Stat stat = stats.getOrDefault(type, getDefaultStat(type));
        stat.normalize(player);
        stats.put(type, stat);
    }

    public void normalize(PlayerSD player) {
        for (Stat stat : this) {
            stat.normalize(player);
        }
    }

    public void reset() {
        for (Stat stat : this) {
            if (stat.getType() instanceof ManaStat) continue;

            stat.set(getDefaultValue(stat.getType()));
        }
    }

    public List<Stat> toCombatList() {
        return this.toList().stream().filter(s -> s.getType() instanceof CombatStat).collect(Collectors.toList());
    }

    public List<Stat> toGatheringList() {
        return this.toList().stream().filter(s -> s.getType() instanceof GatheringStat).collect(Collectors.toList());
    }

    public List<Stat> toWisdomList() {
        return this.toList().stream().filter(s -> s.getType() instanceof WisdomStat).collect(Collectors.toList());
    }

    public List<Stat> toMiscList() {
        return this.toList().stream().filter(s -> s.getType() instanceof MiscStat).collect(Collectors.toList());
    }

    public List<Stat> toList() {
        List<Stat> stats = new ArrayList<>();
        for (StatType stat : StatTypes.STATS) {
            stats.add(this.stats.getOrDefault(stat, new Stat(stat, 0)));
        }
        return stats;
    }

    @Override
    public String toString() {
        return this.toList().toString();
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

    public Stat getHealth() {
        return this.get(StatTypes.HEALTH);
    }

    public Stat getDefense() {
        return this.get(StatTypes.DEFENSE);
    }

    public Stat getTrueDefense() {
        return this.get(StatTypes.TRUE_DEFENSE);
    }

    public Stat getSpeed() {
        return this.get(StatTypes.SPEED);
    }

    public Stat getVitality() {
        return this.get(StatTypes.VITALITY);
    }

    public Stat getIntelligence() {
        return this.get(StatTypes.INTELLIGENCE);
    }

    public Stat getMana() {
        return ((FilledStat) this.get(StatTypes.INTELLIGENCE)).getFiller();
    }

    public Stat getMending() {
        return this.get(StatTypes.MENDING);
    }

    public Stat getDamage() {
        return this.get(StatTypes.DAMAGE);
    }

    public Stat getStrength() {
        return this.get(StatTypes.STRENGTH);
    }

    public Stat getCritDamage() {
        return this.get(StatTypes.CRIT_DAMAGE);
    }

    public Stat getCritChance() {
        return this.get(StatTypes.CRIT_CHANCE);
    }

    public Stat getAbilityDamage() {
        return this.get(StatTypes.ABILITY_DAMAGE);
    }

    public Stat getAttackSpeed() {
        return this.get(StatTypes.ATTACK_SPEED);
    }

    public Stat getFerocity() {
        return this.get(StatTypes.FEROCITY);
    }

    public Stat getMagicFind() {
        return this.get(StatTypes.MAGIC_FIND);
    }

    public Stat getPetLuck() {
        return this.get(StatTypes.PET_LUCK);
    }

    public Stat getSeaCreatureChance() {
        return this.get(StatTypes.SEA_CREATURE_CHANCE);
    }

    public Stat getFishingSpeed() {
        return this.get(StatTypes.FISHING_SPEED);
    }

    public Stat getMiningSpeed() {
        return this.get(StatTypes.MINING_SPEED);
    }

    public Stat getMiningFortune() {
        return this.get(StatTypes.MINING_FORTUNE);
    }

    public Stat getFarmingFortune() {
        return this.get(StatTypes.FARMING_FORTUNE);
    }

    public Stat getForagingFortune() {
        return this.get(StatTypes.FORAGING_FORTUNE);
    }

    public Stat getFarmingWisdom() {
        return this.get(StatTypes.FARMING_WISDOM);
    }

    public Stat getMiningWisdom() {
        return this.get(StatTypes.MINING_WISDOM);
    }

    public Stat getCombatWisdom() {
        return this.get(StatTypes.COMBAT_WISDOM);
    }

    public Stat getForagingWisdom() {
        return this.get(StatTypes.FORAGING_WISDOM);
    }

    public Stat getFishingWisdom() {
        return this.get(StatTypes.FISHING_WISDOM);
    }

    public Stat getEnchantingWisdom() {
        return this.get(StatTypes.ENCHANTING_WISDOM);
    }

    public Stat getAlchemyWisdom() {
        return this.get(StatTypes.ALCHEMY_WISDOM);
    }

    public Stat getTamingWisdom() {
        return this.get(StatTypes.TAMING_WISDOM);
    }

    public Stat getDungeoneeringWisdom() {
        return this.get(StatTypes.DUNGEONEERING_WISDOM);
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        this(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.DAMAGE, damage),
                new Stat(StatTypes.SPEED, strength),
                new Stat(StatTypes.CRIT_DAMAGE, critDamage),
                new Stat(StatTypes.CRIT_CHANCE, critChance),
                new Stat(StatTypes.ATTACK_SPEED, attackSpeed),
                new Stat(StatTypes.FEROCITY, ferocity),
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.SPEED, speed),
                new Stat(StatTypes.INTELLIGENCE, intelligence)
        )));
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity) {
        this(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.DAMAGE, damage),
                new Stat(StatTypes.STRENGTH, strength),
                new Stat(StatTypes.CRIT_DAMAGE, critDamage),
                new Stat(StatTypes.CRIT_CHANCE, critChance),
                new Stat(StatTypes.ATTACK_SPEED, attackSpeed),
                new Stat(StatTypes.FEROCITY, ferocity)
        )));
    }

    public Stats(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double miningSpeed, double miningFortune) {
        this(new ArrayList<>(Arrays.asList(
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

    public Stats(double health, double defense, double trueDefense, double intelligence, double miningSpeed, double miningFortune, double magicFind) {
        this(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.TRUE_DEFENSE, trueDefense),
                new Stat(StatTypes.INTELLIGENCE, intelligence),
                new Stat(StatTypes.MAGIC_FIND, magicFind),
                new Stat(StatTypes.MINING_SPEED, miningSpeed),
                new Stat(StatTypes.MINING_FORTUNE, miningFortune)
        )));
    }

    public Stats(double health, double defense, double speed, double intelligence) {
        this(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.HEALTH, health),
                new Stat(StatTypes.DEFENSE, defense),
                new Stat(StatTypes.SPEED, speed),
                new Stat(StatTypes.INTELLIGENCE, intelligence)
        )));
    }

    public Stats(double miningSpeed, double miningFortune) {
        this(new ArrayList<>(Arrays.asList(
                new Stat(StatTypes.MINING_SPEED, miningSpeed),
                new Stat(StatTypes.MINING_FORTUNE, miningFortune)
        )));
    }
}
