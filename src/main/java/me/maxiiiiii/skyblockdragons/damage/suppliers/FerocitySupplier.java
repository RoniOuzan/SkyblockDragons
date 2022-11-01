package me.maxiiiiii.skyblockdragons.damage.suppliers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FerocitySupplier {
    private double activeFerocity;
    private boolean isFerocityAttack;

    public FerocitySupplier(boolean isFerocity, double activeFerocity) {
        this.activeFerocity = activeFerocity;
        this.isFerocityAttack = isFerocity;
    }

    public FerocitySupplier() {
        this(false, 0);
    }
}
