package me.maxiiiiii.skyblockdragons.util;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlyTo extends BukkitRunnable {
    public Entity entity;
    public Entity target;
    public long denominator;

    public double stopAt;

    public Vector adder;

    public boolean lookAtTarget;

    public FlyTo(Entity entity, Entity target, long denominator, double stopAt, boolean lookAtTarget, Vector adder) {
        this.entity = entity;
        this.target = target;
        this.denominator = denominator;

        this.stopAt = stopAt;

        this.adder = adder;

        this.lookAtTarget = lookAtTarget;

        this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    public FlyTo(Entity entity, Entity target, long denominator, double stopAt, boolean lookAtTarget) {
        this(entity, target, denominator, stopAt, lookAtTarget, new Vector());
    }

    @Override
    public void run() {
        if (target.getLocation().distance(entity.getLocation()) <= stopAt) {
            this.cancel();
            return;
        }
        double x = (target.getLocation().getX() - entity.getLocation().getX()) / denominator;
        double y = (target.getLocation().add(0, 0.5, 0).add(adder).getY() - entity.getLocation().getY()) / denominator;
        double z = (target.getLocation().getZ() - entity.getLocation().getZ()) / denominator;

        Location targetLocation = entity.getLocation();
        targetLocation.add(x, y, z);
        if (lookAtTarget)
            targetLocation.setDirection(new Vector(x, y, z));
        entity.teleport(targetLocation);
    }
}
