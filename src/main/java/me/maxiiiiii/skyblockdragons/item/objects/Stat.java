package me.maxiiiiii.skyblockdragons.item.objects;

public class Stat {
    public double amount;
    public StatType type;

    public Stat(double amount, StatType type) {
        this.amount = amount;
        this.type = type;
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

    public void normalize() {
        this.amount = Math.floor(this.amount * 10d) / 10d;
        if (type == StatType.ATTACK_SPEED && this.amount > 100)
            this.amount = 100;
        if (type == StatType.SPEED && this.amount > 500)
            this.amount = 500;
    }

    @Override
    public String toString() {
        return this.amount + "" + (type.isPercentage() ? "%" : "");
    }
}
