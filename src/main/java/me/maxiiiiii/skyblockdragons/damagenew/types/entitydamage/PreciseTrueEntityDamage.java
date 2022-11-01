package me.maxiiiiii.skyblockdragons.damagenew.types.entitydamage;

import me.maxiiiiii.skyblockdragons.damagenew.interfaces.TrueDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class PreciseTrueEntityDamage extends PreciseEntityDamage implements TrueDamage {
    public PreciseTrueEntityDamage(EntitySD victim, double baseDamage) {
        super(victim, baseDamage);
    }

    @Override
    protected double getDamageReduction() {
        return damageReduction(victim);
    }
}
