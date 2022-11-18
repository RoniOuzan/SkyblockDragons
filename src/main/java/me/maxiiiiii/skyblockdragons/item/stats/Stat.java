package me.maxiiiiii.skyblockdragons.item.stats;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.inventory.ItemStack;

public class Stat {
    private final StatType type;
    private double amount;

    public Stat(StatType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public Stat(double amount, StatType type) {
        this(type, amount);
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

    public String getString() {
        return (this.amount + "").replace(".0", "");
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

    public void multiply(double multiplier) {
        this.amount *= multiplier;
    }

    public void normalize(PlayerSD player) {
        if (this.amount >= this.type.getMaxLevel(player)) {
            this.amount = this.type.getMaxLevel(player);
        } else {
            this.amount = Math.round(this.amount * 10) / 10d;
        }
    }

    public void reset(PlayerSD player) {
        this.amount = this.type.getBase(player);
    }

    public boolean isEmpty() {
        return this.amount == 0;
    }

    public ItemStack getItem() {
        return this.type.getItem(this.amount);
    }

    public String toStringLore() {
        return this.type.toStringLore(this.amount);
    }

    @Override
    public String toString() {
        return this.type.toString(this.amount);
    }
}
