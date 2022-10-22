package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Multiplier {
    private double baseMultiplier;
    private double postMultiplier;

    public Multiplier() {
        this.reset();
    }

    public void addBaseMultiplier(double add) {
        this.baseMultiplier += add;
    }

    public void addPostMultiplier(double add) {
        this.postMultiplier += add;
    }

    public double multiply(double amount) {
        amount *= this.baseMultiplier / 100;
        amount *= this.postMultiplier / 100;
        return amount;
    }

    public void reset() {
        this.baseMultiplier = 100;
        this.postMultiplier = 100;
    }
}
