package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

@Getter
public class MeleeEntityDamageEntity extends NormalEntityDamageEntity {
    public MeleeEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity) {
        super(attacker, victim, isFerocity);
    }

    public MeleeEntityDamageEntity(EntitySD attacker, EntitySD victim) {
        super(attacker, victim);
    }
}
