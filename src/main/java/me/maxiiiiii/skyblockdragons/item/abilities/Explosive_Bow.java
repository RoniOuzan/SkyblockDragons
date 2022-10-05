package me.maxiiiiii.skyblockdragons.item.abilities;

import lombok.var;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class Explosive_Bow implements Listener {
    private String bow = "EXPLOSIVE_BOW";
    private double radius = 3;

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType() == EntityType.ARROW) {
                Player player = (Player) e.getEntity().getShooter();
                ItemStack item = player.getEquipment().getItemInMainHand();
                Projectile projectile = e.getEntity();
                if (Functions.getId(item).equals(bow)) {
                    projectile.addScoreboardTag(bow);
                }
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            Projectile projectile = event.getEntity();
            if (projectile.getScoreboardTags().contains(bow)){
//                player.sendMessage("Explosive bow!");
                player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, projectile.getLocation(), 1);
                for (Entity entity : projectile.getNearbyEntities(radius, radius, radius)) {
                    if (entity instanceof Creature){
                        Creature creature = (Creature) entity;
                        EntityDamageEntityEvent playerDamageEntity = new EntityDamageEntityEvent(player, creature, Damage.DamageType.PROJECTILE, 1, false);
                        Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);
                    }
                }
            }
        }
    }
}
