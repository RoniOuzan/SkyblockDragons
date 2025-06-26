package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DragonAI extends BukkitRunnable {
    public enum Phase {
        CIRCLING, WAITING
    }

    private final EntitySD dragon;

    private Phase phase;
    private double waitingStartTime;

    private Circling circling;
    private final Cooldown<EntitySD> fireballCooldown;

    public DragonAI(EntitySD dragon) {
        this.dragon = dragon;
        this.phase = Phase.CIRCLING;
        this.waitingStartTime = SkyblockDragons.getCurrentTimeInSeconds();
        this.circling = new Circling(dragon);

        this.fireballCooldown = new Cooldown<>();

        this.runTaskTimer(SkyblockDragons.plugin, 0L, 2L);
    }

    @Override
    public void run() {
        if (this.dragon == null || this.dragon.isDead()) {
            this.cancel();
            return;
        }

        if (this.phase == Phase.CIRCLING && this.circling != null && this.circling.isFinished()) {
            this.phase = Phase.WAITING;

            this.circling = null;
            this.waitingStartTime = SkyblockDragons.getCurrentTimeInSeconds();
            Functions.While(() -> this.phase == Phase.WAITING, 1L, i -> dragon.setVelocity(new Vector()));
        } else if (this.phase == Phase.WAITING && SkyblockDragons.getCurrentTimeInSeconds() - this.waitingStartTime >= 8) {
            this.phase = Phase.CIRCLING;
            this.circling = new Circling(this.dragon);

            ((EntityDragon) this.dragon.getMaterial()).strikeAbility(this.dragon);
        }

        switch (this.phase) {
            case CIRCLING:
                if (this.circling != null) {
                    this.circling.update();
                }
                break;
            case WAITING:
                if (!Functions.cooldown(dragon, fireballCooldown, 300, false)) {
                    Fireball fireball = dragon.getWorld().spawn(dragon.getLocation().add(dragon.getLocation().getDirection().multiply(5)), Fireball.class);
                    fireball.setVelocity(dragon.getLocation().getDirection().multiply(2));
                    fireball.setShooter(dragon); // Optional, useful for damage attribution
                    fireball.setIsIncendiary(false); // Prevent fire spread
                    fireball.setYield(2F); // Explosion size
                }
                break;
        }
    }

    private static class Circling {
        private int index;
        private int orbitTicks;
        private final List<Location> spiralPath;
        private final Location center;
        private final EntitySD dragon;

        private final static int ORBIT_DURATION_TICKS = 60;
        private final static double ORBIT_RADIUS = 30;
        private final static int SPIRAL_ROTATIONS = 2;
        private final static double VELOCITY = 24 * (1.0 / 20.0); // blocks per tick

        public Circling(EntitySD dragon) {
            this.index = 0;
            this.orbitTicks = 0;
            this.center = TheEnd.DRAGON_SPAWN;
            this.dragon = dragon;

            double x = Functions.randomDouble(-40, 40);
            double y = Functions.randomDouble(75, 120);
            double z = Functions.randomDouble(-40, 40);
            Location target = new Location(this.center.getWorld(), x, y, z);

            this.spiralPath = generateInwardSpiral(dragon.getLocation(), target, 45, SPIRAL_ROTATIONS, 80);
        }

        public void update() {
            Location current = this.dragon.getLocation();
            Location next;

            if (orbitTicks < ORBIT_DURATION_TICKS) {
                double angle = 2 * Math.PI * orbitTicks / ORBIT_DURATION_TICKS;

                double ox = Math.cos(angle) * ORBIT_RADIUS;
                double oz = Math.sin(angle) * ORBIT_RADIUS;
                double oy = center.getY() + 10;

                Location target = spiralPath.get(0);
                next = new Location(center.getWorld(), target.getX() + ox, oy, target.getZ() + oz);
                orbitTicks++;
            } else {
                if (index >= spiralPath.size()) return;
                next = spiralPath.get(index);

                index++;
            }

            Vector velocity = next.toVector().subtract(current.toVector()).normalize().multiply(VELOCITY);
            this.dragon.setVelocity(velocity);

            // Rotate dragon to face movement direction
            this.dragon.teleport(this.dragon.getLocation().setDirection(velocity));
        }

        public boolean isFinished() {
            return orbitTicks >= ORBIT_DURATION_TICKS && index >= spiralPath.size() * 0.8 &&
                    dragon.getLocation().distance(spiralPath.get(spiralPath.size() - 1)) <= 5;
        }

        public List<Location> generateInwardSpiral(Location start, Location end, double maxRadius, int rotations, int points) {
            List<Location> path = new ArrayList<>();

            Vector mainDirection = end.toVector().subtract(start.toVector());
            double totalDistance = mainDirection.length();
            mainDirection.normalize();

            // Perpendicular vector in XZ for spiraling
            Vector perp = new Vector(-mainDirection.getZ(), 0, mainDirection.getX());

            for (int i = 0; i <= points; i++) {
                double t = (double) i / points;

                double radius = (1 - t) * maxRadius;
                double angle = t * rotations * 2 * Math.PI;

                Vector spiralOffset;
                if (t == 1) {
                    // Ensure exact start and end
                    spiralOffset = new Vector(0, 0, 0);
                } else {
                    spiralOffset = perp.clone().multiply(Math.cos(angle))
                            .add(mainDirection.clone().crossProduct(perp).multiply(Math.sin(angle)))
                            .normalize().multiply(radius);
                }

                Vector along = mainDirection.clone().multiply(t * totalDistance);
                Vector finalPos = start.toVector().add(along).add(spiralOffset);

                double y = start.getY() + t * (end.getY() - start.getY());
                Location loc = new Location(start.getWorld(), finalPos.getX(), y, finalPos.getZ());
                path.add(loc);
            }

            return path;
        }

    }

}

/*
@Override
    public void run() {
        if (this.dragon == null || this.dragon.isDead()) {
            this.cancel();
            return;
        }

        Location location = this.dragon.getLocation();
        Vector acceleration = getRandomAcceleration(location);
        this.velocity.add(acceleration.multiply(PERIOD));
        if (this.velocity.length() > MAX_VELOCITY) {
            this.velocity.multiply(MAX_VELOCITY / this.velocity.length());
        }
        location.add(this.velocity.clone().multiply(PERIOD));

        this.dragon.teleport(location);
    }

    private Vector getRandomAcceleration(Location location) {
        double accelAngle = getAccelerationDirection(location);
        return new Vector(Math.cos(accelAngle), 0, Math.sin(accelAngle)).multiply(ACCELERATION);
    }

    private double getAccelerationDirection(Location location) {
        double distance = location.length();
        double percent = distance / MAX_DISTANCE;
        if (percent < 0.5) {
            return Math.atan2(this.velocity.getZ(), this.velocity.getX());
        }

        percent = 1 - Math.max((percent * 2) - 1, 0);
        double range = Functions.k90 + (Functions.k90 * percent);
        double offset = Math.atan2(location.getZ(), location.getX());

        return (Functions.k180 - Functions.randomDouble(-range, range)) + offset;
    }
 */
