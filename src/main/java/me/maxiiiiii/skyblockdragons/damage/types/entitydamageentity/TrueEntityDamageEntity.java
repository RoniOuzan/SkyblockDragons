package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.interfaces.TrueDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;

@Getter
public class TrueEntityDamageEntity extends RangeEntityDamageEntity implements TrueDamage {
    public TrueEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, boolean isFerocity) {
        super(attacker, victim, equipment, isFerocity);
    }

    public TrueEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment) {
        this(attacker, victim, equipment, false);
    }

    @Override
    protected double getDamageReduction() {
        return damageReduction(victim);
    }
}
