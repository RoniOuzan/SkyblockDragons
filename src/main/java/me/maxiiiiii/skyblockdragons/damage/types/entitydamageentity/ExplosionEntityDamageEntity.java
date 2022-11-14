package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.damage.interfaces.ExplosionDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class ExplosionEntityDamageEntity extends NormalEntityDamageEntity implements ExplosionDamage {
    public ExplosionEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity) {
        super(attacker, victim, isFerocity);
    }

    public ExplosionEntityDamageEntity(EntitySD attacker, EntitySD victim) {
        super(attacker, victim);
    }
}
