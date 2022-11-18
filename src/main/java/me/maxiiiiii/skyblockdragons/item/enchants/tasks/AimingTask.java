package me.maxiiiiii.skyblockdragons.item.enchants.tasks;

import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AimingTask extends BukkitRunnable {

    private Projectile projectile;
    private LivingEntity target;
    private short level;
    private long time;

    public AimingTask(Projectile projectile, short level) {
        this.projectile = projectile;
        this.level = level;
        int radius = level*2;
        time = System.currentTimeMillis();
        findTarget(projectile, radius);
    }

    private void findTarget(Projectile projectile, int radius) {
        double distance = Double.MAX_VALUE;
        double d;
        for (Entity entity : projectile.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Creature && !(entity instanceof Enderman) || entity instanceof EnderDragon) {
                d = projectile.getLocation().distance(entity.getLocation());
                if (d < distance) {
                    distance = d;
                    this.target = (LivingEntity) entity;
                }
            }
        }
    }

    @Override
    public void run() {
        if (target != null && target.isDead() || System.currentTimeMillis() - time >= 5000) {
            cancel();
            return;
        }
        findTarget(projectile, level*2);
        if (target != null) {
            Vector targetVector = target.getEyeLocation().subtract(projectile.getLocation()).toVector().normalize();
//            targetVector.setY(0);
            projectile.setVelocity(targetVector);
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
