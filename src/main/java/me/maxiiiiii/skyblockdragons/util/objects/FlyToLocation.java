package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlyToLocation extends BukkitRunnable {
    private final Entity entity;
    private final Location target;
    private final long ticks;

    private final double stopAt;

    private final boolean lookAtTarget;

    private final double x;
    private final double y;
    private final double z;

    public FlyToLocation(Entity entity, Location target, long ticks, double stopAt, boolean lookAtTarget) {
        this.entity = entity;
        this.target = target;
        this.ticks = ticks;

        this.stopAt = stopAt;

        this.lookAtTarget = lookAtTarget;

        this.x = (target.getX() - entity.getLocation().getX()) / ticks;
        this.y = (target.getY() - entity.getLocation().getY()) / ticks;
        this.z = (target.getZ() - entity.getLocation().getZ()) / ticks;

        this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    @Override
    public void run() {
        if (target.distance(entity.getLocation()) <= stopAt) {
            this.cancel();
            return;
        }
        if (target.getWorld() != entity.getWorld()) {
            entity.remove();
            return;
        }

        Location targetLocation = entity.getLocation();
        targetLocation.add(x, y, z);
        if (lookAtTarget)
            if (entity instanceof EnderDragon)
                targetLocation.setDirection(new Vector(-x, -y, -z));
            else
                targetLocation.setDirection(new Vector(x, y, z));
        entity.teleport(targetLocation);
    }
}
