package me.maxiiiiii.skyblockdragons.damage.suppliers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FerocitySupplier {
    private boolean isFerocityAttack;
    private double activeFerocity;

    public FerocitySupplier(boolean isFerocity, double activeFerocity) {
        this.isFerocityAttack = isFerocity;
        this.activeFerocity = activeFerocity;
    }

    public FerocitySupplier() {
        this(false, 0);
    }
}
