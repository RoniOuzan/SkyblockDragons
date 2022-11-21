package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.interfaces.TrueDamage;
import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

@Getter
public class TrueEntityDamageEntity extends NormalEntityDamageEntity implements TrueDamage {
    public TrueEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity) {
        super(attacker, victim, isFerocity);
    }

    public TrueEntityDamageEntity(EntitySD attacker, EntitySD victim) {
        super(attacker, victim);
    }

    @Override
    protected double getDamageReduction() {
        return damageReduction(victim);
    }
}
