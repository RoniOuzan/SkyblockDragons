package me.maxiiiiii.skyblockdragons.damagenew;

import me.maxiiiiii.skyblockdragons.damagenew.types.entitydamage.PreciseExplosionEntityDamage;
import me.maxiiiiii.skyblockdragons.damagenew.types.entitydamage.PreciseFallEntityDamage;
import me.maxiiiiii.skyblockdragons.damagenew.types.entitydamage.PreciseFireEntityDamage;
import me.maxiiiiii.skyblockdragons.damagenew.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damagenew.types.entitydamageentity.ProjectileEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class VanillaDamageListener implements Listener { // TODO: attack speed

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof LivingEntity) {
            EntitySD victim = EntitySD.get(e.getEntity());

            EntityDamage damage = null;
            if (e.getDamager() instanceof LivingEntity) {
                EntitySD attacker = EntitySD.get(e.getDamager());
                damage = new MeleeEntityDamageEntity(attacker, victim, attacker.getItems());
            } else {
                if (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player) {
                    Projectile projectile = (Projectile) e.getDamager();
                    EntitySD attacker = EntitySD.get((Entity) projectile.getShooter());
                    damage = new ProjectileEntityDamageEntity(attacker, victim, attacker.getItems(), projectile);
                }
            }

            if (damage == null) return;
            Bukkit.getPluginManager().callEvent(new EntityDamageEvent(damage));
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onDamage(org.bukkit.event.entity.EntityDamageEvent e) {
        if (e.getEntity() instanceof LivingEntity) {
            EntitySD victim = EntitySD.get(e.getEntity());

            EntityDamage damage = null;
            if (e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.LAVA) {
                damage = new PreciseFireEntityDamage(victim);
            } else if (e.getCause() == DamageCause.FALL) {
                damage = new PreciseFallEntityDamage(victim, e.getDamage());
            } else if (e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.BLOCK_EXPLOSION) {
                damage = new PreciseExplosionEntityDamage(victim, e.getDamage());
            }

            if (damage == null) return;
            Bukkit.getPluginManager().callEvent(new EntityDamageEvent(damage));
        }
    }
}
