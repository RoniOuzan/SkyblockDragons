package me.maxiiiiii.skyblockdragons.damage.types.entitydamage;

import me.maxiiiiii.skyblockdragons.damage.interfaces.FireDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class PreciseFireEntityDamage extends PreciseEntityDamage implements FireDamage {
    public PreciseFireEntityDamage(EntitySD victim) {
        super(victim, 0);
    }

    @Override
    protected double calculateDamageFormula() {
        return victim.getMaxHealth() / 50d;
    }
}
