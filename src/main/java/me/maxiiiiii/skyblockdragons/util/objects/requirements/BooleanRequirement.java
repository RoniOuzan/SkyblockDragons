package me.maxiiiiii.skyblockdragons.util.objects.requirements;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.function.Function;

public class BooleanRequirement extends Requirement {
    private final Function<PlayerSD, Boolean> supplier;

    public BooleanRequirement(Function<PlayerSD, Boolean> supplier) {
        super(RequirementType.BOOLEAN);
        this.supplier = supplier;
    }

    @Override
    public boolean hasRequirement(PlayerSD player) {
        return this.supplier.apply(player);
    }

    @Override
    public String toString() {
        return "";
    }
}
