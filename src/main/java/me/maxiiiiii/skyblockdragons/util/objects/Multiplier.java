package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Multiplier {
    private double baseMultiplier;
    private double baseReducer;
    private double postMultiplier;
    private double postReducer;

    public Multiplier() {
        this.reset();
    }

    public void addBase(double add) {
        if (add < 0) {
            this.baseReducer -= add; // - cuz the add is on -
        } else {
            this.baseMultiplier += add;
        }
    }

    public void addPost(double add) {
        if (add < 0) {
            this.postReducer -= add; // - cuz the add is on -
        } else {
            this.postMultiplier += add;
        }
    }

    public double multiply(double amount) {
        amount *= this.baseMultiplier / 100;
        amount *= this.postMultiplier / 100;
        amount *= 1 / (this.baseReducer / 100);
        amount *= 1 / (this.postReducer / 100);
        return amount;
    }

    public void reset() {
        this.baseMultiplier = 100;
        this.postMultiplier = 100;
        this.baseReducer = 100;
        this.postReducer = 100;
    }
}
