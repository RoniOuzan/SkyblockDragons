package me.maxiiiiii.skyblockdragons.damage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.maxiiiiii.skyblockdragons.util.Functions.createHologram;
import static me.maxiiiiii.skyblockdragons.util.Functions.particleLine;
import static me.maxiiiiii.skyblockdragons.util.Functions.rainbow;
import static me.maxiiiiii.skyblockdragons.util.Functions.randomDouble;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.getPlayer;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;

public class Damage implements Listener {
    public double damage(PlayerSD player, Entity entity, double activeFerocity) {
        double damage;
        boolean critHit = false;

        double baseMultiplayer = 1;
        double postMultiplayer = 1;

        damage = (5 + player.getDamage()) * (1 + (player.getStrength() / 100)); // damage formula / calculation

        baseMultiplayer += player.getSkill().getCombatSkill().getLevel() * 0.04;

        if (randomDouble(1, 100) <= player.getCritChance()) {
            damage = damage * (1 + (player.getCritDamage() / 100));
            critHit = true;
        }
        damage = Math.floor(damage);

        String damageDisplay = ChatColor.GRAY + "" + ((int) damage);
        if (critHit) {
            damageDisplay = rainbow(((int) damage) + "");
            damageDisplay = ChatColor.WHITE + "✧" + damageDisplay + ChatColor.WHITE + "✧";
        }

        Location hologram = entity.getLocation().clone();
        double random = randomDouble(1,1.5);
        hologram.add(entity.getLocation().clone().getDirection().multiply(random));
        hologram.setY(entity.getLocation().getY() + random);
        createHologram(hologram, damageDisplay, 20);

        EntityDamageEvent event = new EntityDamageByEntityEvent(player, entity, EntityDamageEvent.DamageCause.FLY_INTO_WALL, 1);
        entity.setLastDamageCause(event);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (activeFerocity > 0) {
            double chance = randomDouble(0, 100);
            if (chance <= activeFerocity) {
                player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 1f);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        damage(player, entity, activeFerocity - 100);
                        player.playSound(player.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 1f, 10f);
                        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 0.25f, 0.25f);
                        Location start = entity.getLocation().clone();
                        Location end = entity.getLocation().clone();

                        if (((int) activeFerocity * 1.1) % 2 == 0) {
                            start.add(1.5, 0.5, 0);
                            end.add(-1.5, -1.5, 0);
                        } else {
                            start.add(-1.5, 0.5, 0);
                            end.add(1.5, -1.5, 0);
                        }

                        particleLine(start, end, Particle.REDSTONE, 155, 0, 0);
                    }
                }.runTaskLater(SkyblockDragons.getInstance(), 5L);
            }
        }
        return ((int) damage);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (e.getEntity() instanceof Creature && e.getEntityType() != EntityType.PLAYER) {
                PlayerSD player = SkyblockDragons.getPlayer((Player) e.getDamager());
                player.setScoreboardScores();
                if (e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) return;

                e.setDamage(damage(player, e.getEntity(), players.get(e.getDamager().getUniqueId()).getFerocity()));

                if (e.getEntity().getName().contains("Dummy")) e.setDamage(0);
            }
        }
    }
}
