package me.maxiiiiii.skyblockdragons.player.stats;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Stat;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;

import java.util.AbstractList;

@Getter
public class PlayerStats extends Stats {
    public Stat mana;
    private final PlayerSD player;

    public PlayerStats(PlayerSD player, double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double seaCreatureChance, double absorption) {
        super(damage, strength, critDamage, critChance, abilityDamage, abilityScaling, attackSpeed, ferocity, health, defense, trueDefense, speed, intelligence, magicFind, petLuck, miningSpeed, miningFortune, seaCreatureChance, absorption);
        this.mana = new Stat(this.intelligence.amount, StatType.MANA);
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
        Stat output = super.get(stat);
        if (stat == StatType.MANA) output = this.mana;
        return output;
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
    public void reset() {
        super.reset();
        for (AbstractSkill skill : this.player.getSkill()) {
            this.add(skill.getRewards().getStat(), skill.getRewards().getStatAmount());
        }
        this.miningFortune.amount += this.player.getSkill().getMiningSkill().getLevel() * 4;
    }
}
