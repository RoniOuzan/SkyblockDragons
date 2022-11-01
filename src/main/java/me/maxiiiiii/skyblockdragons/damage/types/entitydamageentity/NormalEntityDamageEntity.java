package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.suppliers.EntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;

public abstract class NormalEntityDamageEntity extends EntityDamageEntity implements DamageCritable {
    public NormalEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, boolean isFerocity) {
        super(attacker, victim, equipment, isFerocity);
    }

    public NormalEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment) {
        this(attacker, victim, equipment, false);
    }

    @Override
    protected double calculateDamageFormula() {
        return getDefaultDamageFormula(attacker);
    }
}
