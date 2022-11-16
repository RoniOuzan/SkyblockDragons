package me.maxiiiiii.skyblockdragons.player.stats;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PlayerStats extends Stats {
    @Getter(AccessLevel.NONE)
    private final Map<StatType, Multiplier> multiplayer;
    private final PlayerSD player;

    public PlayerStats(PlayerSD player) {
        super();
        this.player = player;
        this.multiplayer = new HashMap<>();
    }

    @Override
    protected double getDefaultValue(StatType type) {
        return type.getBase(player);
    }

    @Override
    public void reset(PlayerSD player) {
        super.reset(player);
        multiplayer.clear();
    }

    public void addMultiplier(StatType statType, double base, double post) {
        Multiplier multiplier = multiplayer.getOrDefault(statType, new Multiplier());
        multiplier.addBase(base);
        multiplier.addPost(post);
        this.multiplayer.put(statType, multiplier);
    }

    public void addMultiplier(StatType statType, double base) {
        this.addMultiplier(statType, base, 0);
    }

    public void addAllStatsMultipliers(double base, double post) {
        for (Stat stat : this) {
            this.addMultiplier(stat.getType(), base, post);
        }
    }

    public void addCombatMultipliers(double base, double post) {
        for (Stat stat : this.toCombatList()) {
            this.addMultiplier(stat.getType(), base, post);
        }
    }

    public void addGatheringMultipliers(double base, double post) {
        for (Stat stat : this.toGatheringList()) {
            this.addMultiplier(stat.getType(), base, post);
        }
    }

    public void addMiscMultipliers(double base, double post) {
        for (Stat stat : this.toMiscList()) {
            this.addMultiplier(stat.getType(), base, post);
        }
    }

    public void addWisdomMultipliers(double base, double post) {
        for (Stat stat : this.toWisdomList()) {
            this.addMultiplier(stat.getType(), base, post);
        }
    }

    public void applyMultipliers() {
        for (StatType statType : multiplayer.keySet()) {
            this.get(statType).set(multiplayer.get(statType).multiply(this.get(statType).get()));
        }
    }
}
