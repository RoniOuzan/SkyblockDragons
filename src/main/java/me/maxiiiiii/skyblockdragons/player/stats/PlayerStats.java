package me.maxiiiiii.skyblockdragons.player.stats;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Stat;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.List;

@Getter
public class PlayerStats extends Stats {
    public Stat mana;
    private final StatsMultiplayer multiplayer;
    private final PlayerSD player;

    public PlayerStats(PlayerSD player, double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double farmingFortune, double foragingFortune, double seaCreatureChance, double absorption) {
        super(damage, strength, critDamage, critChance, abilityDamage, abilityScaling, attackSpeed, ferocity, health, defense, trueDefense, speed, intelligence, magicFind, petLuck, miningSpeed, miningFortune, farmingFortune, foragingFortune, seaCreatureChance, absorption);
        this.mana = new Stat(this.intelligence.amount, StatType.MANA);
        this.multiplayer = new StatsMultiplayer();
        this.player = player;
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
        this.multiplayer.apply(this);
    }
}
