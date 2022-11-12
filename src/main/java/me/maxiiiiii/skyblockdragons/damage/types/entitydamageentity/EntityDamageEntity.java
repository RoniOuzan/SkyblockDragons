package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import lombok.Getter;
import lombok.Setter;
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
    protected PlayerStats stats;
    protected Equipment equipment;
    protected final FerocitySupplier ferocity;
    private boolean isCritHit;

    public EntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier ferocity) {
        super(victim, 1);
        this.attacker = attacker;
        this.stats = attacker instanceof PlayerSD ? ((PlayerSD) attacker).getStats() : null;
        this.equipment = attacker.getItems();
        this.ferocity = ferocity;
        this.isCritHit = calculateIsCritHit();
    }

    public EntityDamageEntity(EntitySD attacker, EntitySD victim) {
        this(attacker, victim, new FerocitySupplier());
    }

    private boolean calculateIsCritHit() {
        if (attacker instanceof PlayerSD && this instanceof DamageCritable) {
            return Math.random() <= this.stats.getCritChance().get() / 100;
        }
        return false;
    }

    @Override
    protected abstract double calculateDamageFormula();

    @Override
    protected double getDamageReduction() {
        if (victim instanceof PlayerSD) {
            double defense = ((PlayerSD) victim).getStats().getDefense().get();
            return 1 - (defense / (defense + 100));
        }
        return 1;
    }

    @Override
    public long getFinalDamage() {
        double damage = calculateDamageFormula();

        if (this.isCritHit()) {
            damage *= 1 + (this.stats.getCritDamage().get() / 100);
        }

        UpdateEntityDamageEvent event = new UpdateEntityDamageEvent(this);
        Bukkit.getPluginManager().callEvent(event);

        damage *= getDamageReduction();

        return (long) damage;
    }

    protected double getDefaultDamageFormula() {
        if (attacker instanceof PlayerSD) {
            return 5 + (stats.getDamage().get()) * (1 + (stats.getStrength().get() / 100));
        }
        return attacker.type.getDamage();
    }
}
