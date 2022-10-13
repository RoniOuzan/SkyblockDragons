package me.maxiiiiii.skyblockdragons.util.objects;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlyTo extends BukkitRunnable {
    public enum Type {
        UNTIL, TIMER
    }

    private final Type type;

    private final Entity entity;
    private final Entity target;
    private final Vector targetAdder;
    private final long denominator;

    private final Hologram hologram;
    private final Vector hologramAdder;

    private final double stopAt;

    private final boolean lookAtTarget;

    private final Runnable onFinish;

    private int times;

    private Vector started;

    public FlyTo(Entity entity, Entity target, Vector targetAdder, long denominator, double stopAt, boolean lookAtTarget, Hologram hologram, Vector hologramAdder, Runnable onFinish, Type type) {
        this.type = type;

        this.entity = entity;
        this.target = target;
        this.targetAdder = targetAdder;
        this.denominator = denominator;

        this.stopAt = stopAt;

        this.lookAtTarget = lookAtTarget;

        this.hologram = hologram;
        this.hologramAdder = hologramAdder;

        this.onFinish = onFinish;

        this.times = 0;
        this.started = null;
        this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    public FlyTo(Entity entity, Entity target, long denominator, double stopAt, boolean lookAtTarget, Hologram hologram, Vector hologramAdder) {
        this(entity, target, new Vector(0, 0.5, 0), denominator, stopAt, lookAtTarget, hologram, hologramAdder, null, Type.UNTIL);
    }

    public FlyTo(Entity entity, Entity target, Vector vector, long denominator, double stopAt, boolean lookAtTarget, Runnable onFinish, Type type) {
        this(entity, target, vector, denominator, stopAt, lookAtTarget, null, new Vector(), onFinish, type);
    }

    public FlyTo(Entity entity, Entity target, long denominator, double stopAt, boolean lookAtTarget) {
        this(entity, target, new Vector(0, 0.5, 0), denominator, stopAt, lookAtTarget, null, new Vector(), null, Type.UNTIL);
    }

    @Override
    public void run() {
        this.times++;
        Location location = target.getLocation();
        Location entityLocation = entity.getLocation();
        if (location.getWorld() != entityLocation.getWorld()){
            cancel();
            return;
        }
        if (location.distance(entityLocation) <= stopAt) {
            if (this.onFinish != null) {
                this.onFinish.run();
            }
            this.cancel();
            return;
        }

        if (this.type == Type.TIMER) {
            if (times > denominator) {
                if (this.onFinish != null) {
                    this.onFinish.run();
                }
                this.cancel();
                return;
            }
        }

        if (target.getWorld() != entity.getWorld()) {
            entity.remove();
            return;
        }
        double x = (location.add(this.targetAdder).getX() - entityLocation.getX()) / denominator;
        double y = (location.add(this.targetAdder).getY() - entityLocation.getY()) / denominator;
        double z = (location.add(this.targetAdder).getZ() - entityLocation.getZ()) / denominator;
        if (this.started == null)
            this.started = new Vector(x, y, z);
        if (this.type == Type.TIMER) {
            x = Math.max(Math.abs(x), Math.abs(started.getX())) * Math.signum(x);
            y = Math.max(Math.abs(y), Math.abs(started.getY())) * Math.signum(y);
            z = Math.max(Math.abs(z), Math.abs(started.getZ())) * Math.signum(z);
        }

        entityLocation.add(x, y, z);
        if (lookAtTarget)
            entityLocation.setDirection(new Vector(x, y, z));
        entity.teleport(entityLocation);
        if (this.hologram != null && !this.hologram.isDeleted()) {
            this.hologram.teleport(entityLocation.add(this.hologramAdder));
        }
    }
}
