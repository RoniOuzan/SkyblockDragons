package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlyToLocation extends BukkitRunnable {
    private final Entity entity;
    private final Location lastLocation;
    private final Location target;

    private final long startedAt;
    private final long ticks;
    private final double stopAt;

    private final double x;
    private final double y;
    private final double z;

    public FlyToLocation(Entity entity, Location target, long ticks, double stopAt, boolean lookAtTarget) {
        this.entity = entity;
        this.lastLocation = entity.getLocation();
        this.target = target;

        this.startedAt = System.currentTimeMillis();
        this.ticks = ticks;
        this.stopAt = stopAt;

        this.x = (target.getX() - entity.getLocation().getX()) / ticks;
        this.y = (target.getY() - entity.getLocation().getY()) / ticks;
        this.z = (target.getZ() - entity.getLocation().getZ()) / ticks;

        if (lookAtTarget)
            if (entity.getType() == EntityType.ENDER_DRAGON) {
                this.lastLocation.setDirection(new Vector(-x, -y, -z));
            } else
                this.lastLocation.setDirection(new Vector(x, y, z));

        this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    @Override
    public void run() {
        if (target.distance(entity.getLocation()) <= this.stopAt || System.currentTimeMillis() - this.startedAt > this.ticks * 50) {
            this.cancel();
            return;
        }
        if (target.getWorld() != entity.getWorld()) {
            entity.remove();
            return;
        }

        this.lastLocation.add(x, y, z);
        this.entity.teleport(this.lastLocation);
    }
}
