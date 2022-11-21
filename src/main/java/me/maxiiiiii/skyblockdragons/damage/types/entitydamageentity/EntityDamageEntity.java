package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.damage.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.EntityDamage;
import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Bukkit;

@Getter
@Setter
public abstract class EntityDamageEntity extends EntityDamage {
    protected EntitySD attacker;
    protected PlayerStats attackerStats;
    protected Equipment attackerEquipment;
    protected final FerocitySupplier ferocity;
    private boolean isCritHit;

    public EntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier ferocity) {
        super(victim, 1);
        this.attacker = attacker;
        this.attackerStats = attacker instanceof PlayerSD ? ((PlayerSD) attacker).getStats() : null;
        this.attackerEquipment = attacker.getItems();
        this.ferocity = ferocity;
        this.isCritHit = calculateIsCritHit();
    }

    public EntityDamageEntity(EntitySD attacker, EntitySD victim) {
        this(attacker, victim, new FerocitySupplier());
    }

    private boolean calculateIsCritHit() {
        if (attacker instanceof PlayerSD && this instanceof DamageCritable) {
            return Math.random() <= this.attackerStats.getCritChance().get() / 100;
        }
        return false;
    }

    @Override
    protected abstract double calculateDamageFormula();

    @Override
    public long getFinalDamage() {
        double damage = calculateDamageFormula();

        if (this.isCritHit()) {
            damage *= 1 + (this.attackerStats.getCritDamage().get() / 100);
        }

        UpdateEntityDamageEvent event = new UpdateEntityDamageEntityEvent(this);
        Bukkit.getPluginManager().callEvent(event);

        damage = event.getDamage().getMultiplier().multiply(damage);

        damage *= getDamageReduction();

        return (long) damage;
    }

    protected double getDefaultDamageFormula() {
        if (attacker instanceof PlayerSD) {
            return 5 + (attackerStats.getDamage().get()) * (1 + (attackerStats.getStrength().get() / 100));
        }
        return attacker.material.getDamage();
    }
}
