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
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class VanillaDamageListener implements Listener {
    private static final int COOLDOWN = 500;

    private final Cooldown<EntitySD> fireCooldown = new Cooldown<>();
    private final Cooldown<EntitySD> explosionCooldown = new Cooldown<>();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onDamage(org.bukkit.event.entity.EntityDamageEvent e) {
        e.setDamage(0);

        if (e.getEntity() instanceof LivingEntity) {
            EntitySD victim = EntitySD.get(e.getEntity());
            victim.setNoDamageTicks(0);
            victim.setMaximumNoDamageTicks(0);

            EntityDamage damage = null;
            if (isFireDamage(victim, e)) {
                damage = new PreciseFireEntityDamage(victim);
            } else if (isFallDamage(e)) {
                damage = new PreciseFallEntityDamage(victim, e.getDamage());
            } else if (isExplosionDamage(victim, e)) {
                damage = new PreciseExplosionEntityDamage(victim, e.getDamage());
            } else if (e instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
                if (event.getDamager() instanceof LivingEntity) {
                    if (event.getDamager() instanceof Player && SkyblockDragons.getPlayer((Player) event.getDamager()).isOnHitTick(victim)) {
                        e.setCancelled(true);
                        return;
                    }

                    EntitySD attacker = EntitySD.get(event.getDamager());
                    damage = new MeleeEntityDamageEntity(attacker, victim);
                } else {
                    if (event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
                        if (event.getDamager() instanceof Player && SkyblockDragons.getPlayer((Player) ((Projectile) event.getDamager()).getShooter()).isOnHitTick(victim)) {
                            e.setCancelled(true);
                            return;
                        }

                        Projectile projectile = (Projectile) event.getDamager();
                        EntitySD attacker = EntitySD.get((Entity) projectile.getShooter());
                        damage = new ProjectileEntityDamageEntity(attacker, victim, projectile);
                    }
                }
            }

            if (damage == null) {
                e.setCancelled(true);
                return;
            }
            Bukkit.getPluginManager().callEvent(new EntityDamageEvent(damage));
        }
    }

    private boolean isFireDamage(EntitySD victim, org.bukkit.event.entity.EntityDamageEvent e) {
        return (e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.LAVA) &&
                !Functions.cooldown(victim, fireCooldown, COOLDOWN, false);
    }

    private boolean isFallDamage(org.bukkit.event.entity.EntityDamageEvent e) {
        return e.getCause() == DamageCause.FALL;
    }

    private boolean isExplosionDamage(EntitySD victim, org.bukkit.event.entity.EntityDamageEvent e) {
        return (e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.BLOCK_EXPLOSION) &&
                !Functions.cooldown(victim, explosionCooldown, COOLDOWN, false);
    }
}
