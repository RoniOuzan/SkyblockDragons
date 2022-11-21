package me.maxiiiiii.skyblockdragons.damage.listeners;

import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.damage.interfaces.DamagePing;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.EntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUtil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class DamageListener implements Listener {
    private static final long FEROCITY_DELAY = 5L;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageEvent e) {
        long damage = e.getFinalDamage();
        if (e.getAttacker() instanceof PlayerSD && e.getDamage() instanceof DamagePing)
            ((PlayerSD) e.getAttacker()).playSound(e.getAttacker().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

        e.getVictim().removeHealth(damage);

        String damageDisplay = ChatColor.GRAY + "" + Functions.getNumberFormat(damage);
        if (e.getDamage() instanceof EntityDamageEntity && ((EntityDamageEntity) e.getDamage()).isCritHit()) {
            damageDisplay = Functions.getNumberFormat(damage);
            damageDisplay = rainbowText(damageDisplay);
            damageDisplay = ChatColor.WHITE + "✧" + damageDisplay + ChatColor.WHITE + "✧";
        } else if (e.getDamage() instanceof MagicEntityDamageEntity)
            damageDisplay = ChatColor.DARK_AQUA + "" + Functions.getNumberFormat(damage);

        Location hologram = e.getVictim().getLocation();
        double random = randomDouble(1, 1.5);
        hologram.add(e.getVictim().getLocation().getDirection().multiply(random));
        hologram.setY(e.getVictim().getLocation().getY() + random);

        createHologram(hologram, damageDisplay, 20);

        if (e.getDamage() instanceof EntityDamageEntity &&
                e.getDamage() instanceof DamageCritable &&
                e.getAttacker() instanceof PlayerSD
        ) {
            PlayerSD attacker = (PlayerSD) e.getAttacker();
            EntitySD victim = e.getVictim();
            double ferocity = ((EntityDamageEntity) e.getDamage()).getFerocity().isFerocityAttack() ?
                    ((EntityDamageEntity) e.getDamage()).getFerocity().getActiveFerocity() :
                    attacker.getStats().getFerocity().get() / 100;

            if (Math.random() <= ferocity) {
                attacker.playSound(attacker.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 1f, 10f);
                attacker.playSound(attacker.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 0.25f, 0.25f);

                double ferocityAngle = Functions.randomDouble(0, Math.PI * 2);
                Location start = victim.getEyeLocation()
                        .add(new Vector(Math.cos(ferocityAngle) * 1.2, 0, Math.sin(ferocityAngle) * 1.2));
                Location end = victim.getEyeLocation()
                        .add(new Vector(Math.cos(ferocityAngle + Math.PI) * 1.2, -1.5, Math.sin(ferocityAngle + Math.PI) * 1.2));

                Particles.line(new ParticleUtil(Particle.REDSTONE, 155, 0, 0, 0, 1), start, end, 0.05);

                EntityDamageEntity entityDamage = (EntityDamageEntity) e.getDamage();
                entityDamage.getFerocity().setFerocityAttack(true);
                entityDamage.getFerocity().setActiveFerocity(ferocity - 1);
                Functions.Wait(FEROCITY_DELAY, () -> Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entityDamage)));
            }
        }
    }
}
