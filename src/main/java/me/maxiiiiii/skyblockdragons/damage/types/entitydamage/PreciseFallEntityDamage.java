package me.maxiiiiii.skyblockdragons.damage.types.entitydamage;

import me.maxiiiiii.skyblockdragons.damage.interfaces.TrueDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class PreciseFallEntityDamage extends PreciseEntityDamage implements TrueDamage {
    public PreciseFallEntityDamage(EntitySD victim, double baseDamage) {
        super(victim, baseDamage);
    }

    @Override
    protected double getDamageReduction() {
        return damageReduction(victim);
    }
}
