package me.maxiiiiii.skyblockdragons.player.stats;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Stat;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class PlayerStats extends Stats {
    public Stat mana;
    @Getter(AccessLevel.NONE)
    private final Map<StatType, Multiplier> multiplayer;
    private final PlayerSD player;

    public PlayerStats(PlayerSD player, double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double farmingFortune, double foragingFortune, double seaCreatureChance, double absorption) {
        super(damage, strength, critDamage, critChance, abilityDamage, abilityScaling, attackSpeed, ferocity, health, defense, trueDefense, speed, intelligence, magicFind, petLuck, miningSpeed, miningFortune, farmingFortune, foragingFortune, seaCreatureChance, absorption);
        this.mana = new Stat(this.intelligence.amount, StatType.MANA);
        this.multiplayer = new HashMap<>();
        this.player = player;
    }

    @Override
    public void reset() {
        super.reset();

        multiplayer.clear();
    }

    public void addMultiplier(StatType statType, double base, double post) {
        Multiplier multiplier = multiplayer.getOrDefault(statType, new Multiplier());
        multiplier.addBase(base);
        multiplier.addPost(post);
        this.multiplayer.put(statType, multiplier);
    }

    public void addAllStatsMultipliers(double base, double post) {
        this.addMultiplier(StatType.DAMAGE, base, post);
        this.addMultiplier(StatType.STRENGTH, base, post);
        this.addMultiplier(StatType.CRIT_DAMAGE, base, post);
        this.addMultiplier(StatType.CRIT_CHANCE, base, post);
        this.addMultiplier(StatType.ABILITY_DAMAGE, base, post);
        this.addMultiplier(StatType.ABILITY_SCALING, base, post);
        this.addMultiplier(StatType.ATTACK_SPEED, base, post);
        this.addMultiplier(StatType.FEROCITY, base, post);
        this.addMultiplier(StatType.HEALTH, base, post);
        this.addMultiplier(StatType.DEFENSE, base, post);
        this.addMultiplier(StatType.TRUE_DEFENSE, base, post);
        this.addMultiplier(StatType.SPEED, base, post);
        this.addMultiplier(StatType.INTELLIGENCE, base, post);
        this.addMultiplier(StatType.MAGIC_FIND, base, post);
        this.addMultiplier(StatType.PET_LUCK, base, post);
        this.addMultiplier(StatType.MINING_SPEED, base, post);
        this.addMultiplier(StatType.MINING_FORTUNE, base, post);
        this.addMultiplier(StatType.FARMING_FORTUNE, base, post);
        this.addMultiplier(StatType.FORAGING_FORTUNE, base, post);
        this.addMultiplier(StatType.SEA_CREATURE_CHANCE, base, post);
        this.addMultiplier(StatType.ABSORPTION, base, post);
    }

    public void addDamageMultipliers(double base, double post) {
        this.addMultiplier(StatType.DAMAGE, base, post);
        this.addMultiplier(StatType.STRENGTH, base, post);
        this.addMultiplier(StatType.CRIT_DAMAGE, base, post);
        this.addMultiplier(StatType.CRIT_CHANCE, base, post);
        this.addMultiplier(StatType.ABILITY_DAMAGE, base, post);
        this.addMultiplier(StatType.ABILITY_SCALING, base, post);
        this.addMultiplier(StatType.ATTACK_SPEED, base, post);
        this.addMultiplier(StatType.FEROCITY, base, post);
        this.addMultiplier(StatType.MANA, base, post);
    }

    @Override
    public Stat getStat(StatType stat) {
        Stat output = super.getStat(stat);
        if (stat == StatType.MANA) output = this.mana;
        return output;
    }

    @Override
    public Stat get(StatType stat) {
        if (stat == StatType.MANA)
            return this.mana;
        return super.get(stat);
    }

    @Override
    public Stat get(Stat stat) {
        Stat output = super.get(stat);
        if (stat.type == StatType.MANA) output = this.mana;
        return output;
    }

    @Override
    public void normalize() {
        super.normalize();
        this.mana.normalize();
    }

    @Override
    public List<Stat> toList() {
        List<Stat> list = super.toList();
        list.add(mana);
        return list;
    }

    public void applyMultipliers() {
        for (StatType statType : multiplayer.keySet()) {
            this.get(statType).set(multiplayer.get(statType).multiply(this.get(statType).get()));
        }
    }
}
