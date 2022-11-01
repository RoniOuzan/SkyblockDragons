package me.maxiiiiii.skyblockdragons.damage.listeners;

import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.damage.suppliers.EntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Damage implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent e) {
        double damage = e.getDamage().getFinalDamage();

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
                Location start = victim.getLocation();
                Location end = victim.getLocation();

                if (Math.random() < 0.5) {
                    start.add(1.5, 0.5, 0);
                    end.add(-1.5, -1.5, 0);
                } else {
                    start.add(-1.5, 0.5, 0);
                    end.add(1.5, -1.5, 0);
                }

                Particles.line(new ParticleUil(Particle.REDSTONE, 155, 0, 0, 0, 1), start, end, 0.05);

                EntityDamageEntity entityDamage = (EntityDamageEntity) e.getDamage();
                entityDamage.getFerocity().setFerocityAttack(true);
                entityDamage.getFerocity().setActiveFerocity(ferocity - 100);
                Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entityDamage));
            }
        }
    }
}
