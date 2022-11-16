package me.maxiiiiii.skyblockdragons.item.stats;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.GatheringStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.MiscStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.WisdomStat;
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
    
    public Stat get(StatType type) {
        return stats.getOrDefault(type, new Stat(type, getDefaultValue(type)));
    }

    public Stats set(StatType type, double amount) {
        Stat stat = stats.getOrDefault(type, new Stat(type, getDefaultValue(type)));
        stat.set(amount);
        stats.put(type, stat);
    }

    public void add(StatType type, double amount) {
        Stat stat = stats.getOrDefault(type, new Stat(type, getDefaultValue(type)));
        stat.add(amount);
        stats.put(type, stat);
    }

    public void remove(StatType type, double amount) {
        Stat stat = stats.getOrDefault(type, new Stat(type, getDefaultValue(type)));
        stat.remove(amount);
        stats.put(type, stat);
    }

    public void normalize(PlayerSD player, StatType type) {
        Stat stat = stats.getOrDefault(type, new Stat(type, getDefaultValue(type)));
        stat.normalize(player);
        stats.put(type, stat);
    }

    public void normalize(PlayerSD player) {
        for (Stat stat : this) {
            stat.normalize(player);
        }
    }

    public void reset(PlayerSD player) {
        for (Stat stat : this) {
            stat.reset(player);
        }
    }

    public List<Stat> toCombatList() {
        return this.toList().stream().filter(s -> s.getType() instanceof CombatStat).collect(Collectors.toList());
    }

    public List<Stat> toGatheringList() {
        return this.toList().stream().filter(s -> s.getType() instanceof GatheringStat).collect(Collectors.toList());
    }

    public List<Stat> toMiscList() {
        return this.toList().stream().filter(s -> s.getType() instanceof MiscStat).collect(Collectors.toList());
    }

    public List<Stat> toWisdomList() {
        return this.toList().stream().filter(s -> s.getType() instanceof WisdomStat).collect(Collectors.toList());
    }

    public List<Stat> toList() {
        return new ArrayList<>(this.stats.values());
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
        return this.get(StatTypes.MANA);
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
}
