package me.maxiiiiii.skyblockdragons.damage.types.entitydamage;

import me.maxiiiiii.skyblockdragons.damage.interfaces.ExplosionDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class PreciseExplosionEntityDamage extends PreciseEntityDamage implements ExplosionDamage {
    public PreciseExplosionEntityDamage(EntitySD victim, double baseDamage) {
        super(victim, baseDamage);
    }
}
