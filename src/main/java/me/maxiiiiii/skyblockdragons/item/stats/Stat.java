package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;

@Getter
public class Stat {
    @Getter(AccessLevel.NONE)
    public double amount;
    public StatType type;

    public Stat(double amount, StatType type) {
        this.amount = amount;
        this.type = type;
    }

    public String getStatDisplay() {
        return this.type.getStatDisplay(this.amount);
    }

    public void multiply(double multiplier) {
        this.amount *= multiplier;
    }

    public void set(double amount) {
        this.amount = amount;
    }

    public double get() {
        return this.amount;
    }

    public void increase(double percent) {
        this.amount *= 1 + (percent / 100);
    }

    public void add(double amount) {
        this.set(this.get() + amount);
    }

    public void remove(double amount) {
        this.set(this.get() - amount);
    }

    public void normalize() {
        this.amount = Math.floor(this.amount * 10d) / 10d;
        if (type == StatType.ATTACK_SPEED && this.amount > 100)
            this.amount = 100;
        if (type == StatType.SPEED && this.amount > 500)
            this.amount = 500;
    }

    public boolean isEmpty() {
        return this.amount == 0;
    }

    @Override
    public String toString() {
        return this.amount + "" + (type.isPercentage() ? "%" : "");
    }
}
