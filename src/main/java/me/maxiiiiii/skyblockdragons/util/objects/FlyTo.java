package me.maxiiiiii.skyblockdragons.util.objects;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlyTo extends BukkitRunnable {
    public Entity entity;
    public Entity target;
    public long denominator;

    public Hologram hologram;
    public Vector hologramAdder;

    public double stopAt;

    public boolean lookAtTarget;

    public FlyTo(Entity entity, Entity target, long denominator, double stopAt, boolean lookAtTarget, Hologram hologram, Vector hologramAdder) {
        this.entity = entity;
        this.target = target;
        this.denominator = denominator;

        this.stopAt = stopAt;

        this.lookAtTarget = lookAtTarget;

        this.hologram = hologram;
        this.hologramAdder = hologramAdder;

        this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    public FlyTo(Entity entity, Entity target, long denominator, double stopAt, boolean lookAtTarget) {
        this(entity, target, denominator, stopAt, lookAtTarget, null, new Vector());
    }

    @Override
    public void run() {
        if (target.getLocation().distance(entity.getLocation()) <= stopAt) {
            this.cancel();
            return;
        }
        if (target.getWorld() != entity.getWorld()) {
            entity.remove();
            return;
        }
        double x = (target.getLocation().getX() - entity.getLocation().getX()) / denominator;
        double y = (target.getLocation().add(0, 0.5, 0).getY() - entity.getLocation().getY()) / denominator;
        double z = (target.getLocation().getZ() - entity.getLocation().getZ()) / denominator;

        Location targetLocation = entity.getLocation();
        targetLocation.add(x, y, z);
        if (lookAtTarget)
            targetLocation.setDirection(new Vector(x, y, z));
        entity.teleport(targetLocation);
        if (this.hologram != null && !this.hologram.isDeleted()) {
            this.hologram.teleport(entity.getLocation().add(this.hologramAdder));
        }
    }
}
