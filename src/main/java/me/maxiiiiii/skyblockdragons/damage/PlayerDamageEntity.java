package me.maxiiiiii.skyblockdragons.damage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageEntity extends EntityDamageByEntityEvent {
    public boolean ferocity;
    public Damage.DamageType damageType;

    public PlayerDamageEntity(Player attacker, Entity victim, Damage.DamageType damageType, double damage, boolean isFerocity) {
        super(attacker, victim, DamageCause.ENTITY_ATTACK, damage);
        this.ferocity = isFerocity;
        this.damageType = damageType;
    }

    public boolean isFerocity() {
        return this.ferocity;
    }
}
