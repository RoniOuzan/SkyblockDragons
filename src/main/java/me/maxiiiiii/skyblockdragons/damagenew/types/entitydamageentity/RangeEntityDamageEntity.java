package me.maxiiiiii.skyblockdragons.damagenew.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;

public class RangeEntityDamageEntity extends NormalEntityDamageEntity {
    public RangeEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, boolean isFerocity) {
        super(attacker, victim, equipment, isFerocity);
    }

    public RangeEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment) {
        super(attacker, victim, equipment, false);
    }
}
