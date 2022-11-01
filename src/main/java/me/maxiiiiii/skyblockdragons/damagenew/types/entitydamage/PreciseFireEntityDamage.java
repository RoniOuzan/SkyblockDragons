package me.maxiiiiii.skyblockdragons.damagenew.types.entitydamage;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class PreciseFireEntityDamage extends PreciseEntityDamage {
    public PreciseFireEntityDamage(EntitySD victim) {
        super(victim, 0);
    }

    @Override
    protected double calculateDamageFormula() {
        return victim.getMaxHealth() / 50d;
    }
}
