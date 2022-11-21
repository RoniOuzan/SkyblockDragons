package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.interfaces.DamagePing;
import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class RangeEntityDamageEntity extends NormalEntityDamageEntity implements DamagePing {
    public RangeEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity) {
        super(attacker, victim, isFerocity);
    }

    public RangeEntityDamageEntity(EntitySD attacker, EntitySD victim) {
        super(attacker, victim);
    }
}
