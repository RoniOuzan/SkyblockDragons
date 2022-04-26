package me.maxiiiiii.skyblockdragons.util.objects;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FlyToTimer extends BukkitRunnable {
    private final Entity entity;
    private final Entity target;
    private final Vector targetAdder;
    private final long time;

    private final Hologram hologram;
    private final Vector hologramAdder;

    private final boolean lookAtTarget;

    private final Runnable onFinish;

    private int times;

    private final Vector vector;

    public FlyToTimer(Entity entity, Entity target, Vector targetAdder, long time, boolean lookAtTarget, Hologram hologram, Vector hologramAdder, Runnable onFinish) {
        this.entity = entity;
        this.target = target;
        this.targetAdder = targetAdder;
        this.time = time;

        this.lookAtTarget = lookAtTarget;

        this.hologram = hologram;
        this.hologramAdder = hologramAdder;

        this.onFinish = onFinish;

        this.times = 0;
        this.vector = new Vector(
                (target.getLocation().getX() - entity.getLocation().getX()) / time,
                (target.getLocation().getY() - entity.getLocation().getY()) / time,
                (target.getLocation().getZ() - entity.getLocation().getZ()) / time
        );
        this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }

    public FlyToTimer(Entity entity, Entity target, long time, boolean lookAtTarget, Hologram hologram, Vector hologramAdder) {
        this(entity, target, new Vector(0, 0.5, 0), time, lookAtTarget, hologram, hologramAdder, null);
    }

    public FlyToTimer(Entity entity, Entity target, Vector vector, long time, boolean lookAtTarget, Runnable onFinish) {
        this(entity, target, vector, time, lookAtTarget, null, new Vector(), onFinish);
    }

    public FlyToTimer(Entity entity, Entity target, long time, boolean lookAtTarget) {
        this(entity, target, new Vector(0, 0.5, 0), time, lookAtTarget, null, new Vector(), null);
    }

    @Override
    public void run() {
        times++;
        if (times > this.time) {
            if (this.onFinish != null)
                this.onFinish.run();
            this.cancel();
            return;
        }

        Location targetLocation = entity.getLocation();
        targetLocation.add(vector);
        if (lookAtTarget)
            targetLocation.setDirection(vector);
        entity.teleport(targetLocation);

        if (this.hologram != null && !this.hologram.isDeleted()) {
            this.hologram.teleport(entity.getLocation().add(this.hologramAdder));
        }
    }
}
