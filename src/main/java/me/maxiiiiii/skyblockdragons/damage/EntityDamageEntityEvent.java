package me.maxiiiiii.skyblockdragons.damage;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Getter
public class EntityDamageEntityEvent extends EntityDamageByEntityEvent {
    public boolean ferocity;
    public Damage.DamageType damageType;
    public EntitySD attacker;
    public EntitySD victim;
    public double baseAbilityDamage;
    public double abilityScaling;

    public EntityDamageEntityEvent(Entity attacker, Entity victim, Damage.DamageType damageType, double damage, boolean isFerocity, double baseAbilityDamage, double abilityScaling) {
        super(attacker, victim, DamageCause.ENTITY_ATTACK, damage);
        this.ferocity = isFerocity;
        this.damageType = damageType;
        if (attacker instanceof Player)
            this.attacker = SkyblockDragons.getPlayer((Player) attacker);
        else
            this.attacker = EntitySD.get(attacker);
        if (victim instanceof Player)
            this.victim = SkyblockDragons.getPlayer((Player) victim);
        else
            this.victim = EntitySD.get(victim);
        this.baseAbilityDamage = baseAbilityDamage;
        this.abilityScaling = abilityScaling;
    }

    public EntityDamageEntityEvent(Entity attacker, Entity victim, Damage.DamageType damageType, double damage, boolean isFerocity) {
        this(attacker, victim, damageType, damage, isFerocity, 0, 0);
    }

    public EntitySD getAttacker() {
        return this.attacker;
    }

    public boolean isFerocity() {
        return this.ferocity;
    }
}
