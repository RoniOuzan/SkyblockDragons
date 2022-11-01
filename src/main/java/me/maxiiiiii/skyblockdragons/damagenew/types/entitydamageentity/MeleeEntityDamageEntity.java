package me.maxiiiiii.skyblockdragons.damagenew.types.entitydamageentity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;

@Getter
public class MeleeEntityDamageEntity extends RangeEntityDamageEntity {
    public MeleeEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, boolean isFerocity) {
        super(attacker, victim, equipment, isFerocity);
    }

    public MeleeEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment) {
        this(attacker, victim, equipment, false);
    }
}
