package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;

public class PercentEntityDamageEntity extends TrueEntityDamageEntity {
    private final double percentage;

    public PercentEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier ferocity, double percentage) {
        super(attacker, victim, ferocity);
        this.percentage = percentage;
    }

    public PercentEntityDamageEntity(EntitySD attacker, EntitySD victim, double percentage) {
        this(attacker, victim, new FerocitySupplier(), percentage);
    }

    @Override
    protected double calculateDamageFormula() {
        return victim.getMaxHealth() * (this.percentage / 100);
    }
}
