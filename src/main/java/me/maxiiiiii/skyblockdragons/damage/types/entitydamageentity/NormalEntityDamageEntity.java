package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public abstract class NormalEntityDamageEntity extends EntityDamageEntity implements DamageCritable {
    public NormalEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity) {
        super(attacker, victim, isFerocity);
    }

    public NormalEntityDamageEntity(EntitySD attacker, EntitySD victim) {
        super(attacker, victim);
    }

    @Override
    protected double calculateDamageFormula() {
        return getDefaultDamageFormula(attacker);
    }
}
