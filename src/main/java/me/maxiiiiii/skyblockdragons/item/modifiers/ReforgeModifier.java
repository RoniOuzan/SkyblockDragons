package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;

public class ReforgeModifier extends ItemModifier {
    private final ReforgeType reforge;

    public ReforgeModifier(ReforgeType reforge) {
        super(ReforgeModifier.class);
        this.reforge = reforge;
    }

    public ReforgeModifier() {
        this(ReforgeType.NULL);
    }

    public ReforgeType get() {
        return this.reforge;
    }
}
