package me.maxiiiiii.skyblockdragons.damage.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.EntityDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.PreciseExplosionEntityDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.PreciseFallEntityDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.PreciseFireEntityDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.ProjectileEntityDamageEntity;
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
            victim.setNoDamageTicks(0);
            victim.setMaximumNoDamageTicks(0);

            if (e.getDamager() instanceof Player && SkyblockDragons.getPlayer((Player) e.getDamager()).isOnHitTick(victim)) {
                e.getDamager().sendMessage("attack speed stopped");
                return;
            }

            EntityDamage damage = null;
            if (e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.LAVA) {
                damage = new PreciseFireEntityDamage(victim);
            } else if (e.getCause() == DamageCause.FALL) {
                damage = new PreciseFallEntityDamage(victim, e.getDamage());
            } else if (e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.BLOCK_EXPLOSION) {
                damage = new PreciseExplosionEntityDamage(victim, e.getDamage());
            } else if (e.getDamager() instanceof LivingEntity) {
                EntitySD attacker = EntitySD.get(e.getDamager());
                damage = new MeleeEntityDamageEntity(attacker, victim);
            } else {
                if (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player) {
                    Projectile projectile = (Projectile) e.getDamager();
                    EntitySD attacker = EntitySD.get((Entity) projectile.getShooter());
                    damage = new ProjectileEntityDamageEntity(attacker, victim, projectile);
                }
            }

            if (damage == null) return;
            Bukkit.getPluginManager().callEvent(new EntityDamageEvent(damage));
        }
    }
}
