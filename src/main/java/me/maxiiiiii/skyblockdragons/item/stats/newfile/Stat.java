package me.maxiiiiii.skyblockdragons.item.stats.newfile;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public class Stat {
    private final StatType type;
    private double amount;

    public Stat(StatType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public Stat(StatType type) {
        this(type, 0);
    }

    public StatType getType() {
        return type;
    }

    public double get() {
        return this.amount;
    }

    public void set(double amount) {
        this.amount = amount;
    }

    public void add(double amount) {
        this.amount += amount;
    }

    public void remove(double amount) {
        this.amount -= amount;
    }

    public void normalize(PlayerSD player) {
        if (this.amount >= this.type.getMaxLevel(player)) {
            this.amount = this.type.getMaxLevel(player);
        } else {
            this.amount = Math.round(this.amount * 10) / 10d;
        }
    }

    @Override
    public String toString() {
        return this.type.toString(this.amount);
    }
}
