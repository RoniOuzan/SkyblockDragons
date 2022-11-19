package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Multiplier {
    protected double baseMultiplier;
    protected double baseReducer;
    protected double postMultiplier;
    protected double postReducer;

    public Multiplier() {
        this.reset();
    }

    public void addBase(double add) {
        if (add < 0) {
            this.baseReducer *= 1 - (add / -100); // - cuz the variable is on -
        } else {
            this.baseMultiplier += add;
        }
    }

    public void addPost(double add) {
        if (add < 0) {
            this.postReducer *= 1 - (add / -100); // - cuz the variable is on -
        } else {
            this.postMultiplier += add;
        }
    }

    public double multiply(double amount) {
        amount *= this.baseMultiplier / 100;
        amount *= this.postMultiplier / 100;
        amount *= this.baseReducer;
        amount *= this.postReducer;
        return amount;
    }

    public void reset() {
        this.baseMultiplier = 100;
        this.postMultiplier = 100;
        this.baseReducer = 1;
        this.postReducer = 1;
    }
}
