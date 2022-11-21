package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.HashMap;
import java.util.Map;

public class PlayerBaseStats {
    private final PlayerSD player;
    private final Map<StatType, Double> stats;

    public PlayerBaseStats(PlayerSD player) {
        this.player = player;
        this.stats = new HashMap<>();
    }

    public void set(StatType statType, double amount) {
        this.stats.put(statType, amount);
    }

    public void reset(StatType statType) {
        this.stats.remove(statType);
    }

    public double get(StatType type) {
        return this.stats.getOrDefault(type, this.stats.getOrDefault(type, type.getBase(this.player)));
    }
}
