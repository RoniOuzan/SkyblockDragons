package me.maxiiiiii.skyblockdragons.damagenew.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;

public class ExplosionEntityDamageEntity extends NormalEntityDamageEntity {
    public ExplosionEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, boolean isFerocity) {
        super(attacker, victim, equipment, isFerocity);
    }

    public ExplosionEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment) {
        super(attacker, victim, equipment);
    }
}
