package me.maxiiiiii.skyblockdragons.util.objects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class AIFly extends BukkitRunnable {
    private int i = 0;
    private Entity e;
    private Location loc;
    private long duration;
    private Location startLoc;
    private double dx;
    private double dy;
    private double dz;
    private long times;
    private double x1;
    private double y1;
    private double z1;
    private boolean changeDirection;

    public AIFly(Entity e, Location loc, long duration){
        this.e = e;
        this.loc = loc;
        this.duration = duration;

        this.times = duration / 50;
        this.x1 = e.getLocation().getX();
        double x2 = loc.getX();

        this.y1 = e.getLocation().getY();
        double y2 = loc.getY();

        this.z1 = e.getLocation().getZ();
        double z2 = loc.getZ();

        this.dx = x2 - x1;
        this.dy = y2 - y1;
        this.dz = z2 - z1;
    }

    public AIFly(Entity e, Entity loc, long duration) {
        this(e, loc, duration, false);
    }

    public AIFly(Entity e, Entity loc, long duration, boolean changeDirection) {
        this.changeDirection = changeDirection;
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i > duration) cancel();
                updateAIFLY(e, loc, duration);
                i++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    public void updateAIFLY(Entity e, Entity loc, long duration) {
        this.e = e;
        this.loc = loc.getLocation().add(0, 1, 0);
        this.duration = duration;

        this.times = duration / 50;
        this.x1 = e.getLocation().getX();
        double x2 = loc.getLocation().getX();

        this.y1 = e.getLocation().getY();
        double y2 = loc.getLocation().getY();

        this.z1 = e.getLocation().getZ();
        double z2 = loc.getLocation().getZ();

        this.dx = x2 - x1;
        this.dy = y2 - y1;
        this.dz = z2 - z1;
    }

    @Override
    public void run() {
        double mx;
        double my;
        double mz;
        Location tpLoc;

        mx = x1 + dx / times * i;
        my = y1 + dy / times * i;
        mz = z1 + dz / times * i;
        tpLoc = new Location(e.getWorld(), mx, my, mz);

        if (changeDirection)
            tpLoc.setDirection(new Vector(mx, my, mz));

        e.teleport(tpLoc);
        i++;
        if (i > times){
            this.cancel();
        }
    }
}
