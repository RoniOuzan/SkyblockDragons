package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityAI;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DragonAI extends EntityAI {
    public enum Phase {
        CIRCLING, WAITING
    }

    private Phase phase;
    private double waitingStartTime;
    private Circling circling;
    private final Cooldown<EntitySD> fireballCooldown;

    public DragonAI(EntitySD entity) {
        super(entity, 1);
        this.fireballCooldown = new Cooldown<>();
    }

    @Override
    public void initialize() {
        this.phase = Phase.CIRCLING;
        this.waitingStartTime = SkyblockDragons.getCurrentTimeInSeconds();
        this.circling = new Circling(this.entity);
    }

    @Override
    public void run() {
        if (this.phase == Phase.CIRCLING && this.circling != null && this.circling.isFinished()) {
            this.phase = Phase.WAITING;
            this.circling = null;
            this.waitingStartTime = SkyblockDragons.getCurrentTimeInSeconds();
            Functions.While(() -> this.phase == Phase.WAITING, 1L, i -> this.entity.setVelocity(new Vector()));
        } else if (this.phase == Phase.WAITING && SkyblockDragons.getCurrentTimeInSeconds() - this.waitingStartTime >= 6) {
            this.phase = Phase.CIRCLING;
            this.circling = new Circling(this.entity);
            ((EntityDragon) this.entity.getMaterial()).strikeAbility(this.entity);
        }

        switch (this.phase) {
            case CIRCLING:
                if (this.circling != null) {
//                    this.circling.debugParticles();
                    this.circling.update();
                }
                break;
            case WAITING:
                if (!Functions.cooldown(this.entity, fireballCooldown, 400, false)) {
                    Location start = this.entity.getLocation().add(0, 1.5, 0);
                    PlayerSD target = Functions.getNearestPlayer(this.entity, 64);
                    if (target != null) {
                        Vector dir = target.getLocation().toVector().subtract(start.toVector()).normalize();
                        Fireball fireball = this.entity.getWorld().spawn(start.add(dir.multiply(5)), Fireball.class);
                        fireball.setVelocity(dir);
                        fireball.setShooter(this.entity);
                        fireball.setIsIncendiary(false);
                        fireball.setYield(2F);

                        this.entity.teleport(this.entity.getLocation().setDirection(dir));
                    }
                }
                break;
        }
    }

    private static class Circling {
        private static final int ORBIT_DURATION_TICKS = 60;
        private static final double ORBIT_RADIUS = 20;
        private static final int SPIRAL_ROTATIONS = 3;
        private static final double VELOCITY = 1.5;

        private int index;
        private int orbitTicks;
        private final List<Location> spiralPath;
        private final Location center;
        private final EntitySD dragon;

        public Circling(EntitySD dragon) {
            this.index = 0;
            this.orbitTicks = 0;
            this.center = TheEnd.DRAGON_SPAWN;
            this.dragon = dragon;

            double x = Functions.randomDouble(-40, 40);
            double y = Functions.randomDouble(75, 120);
            double z = Functions.randomDouble(-40, 40);
            Location target = new Location(this.center.getWorld(), x, y, z);

            this.spiralPath = generateBezierSpiral(this.center, target, 45, SPIRAL_ROTATIONS, 160);
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
                next = spiralPath.get(index++);
            }

            Vector velocity = next.toVector().subtract(current.toVector());
            Vector smoothed = velocity.clone().normalize().multiply(VELOCITY);
            this.dragon.setVelocity(smoothed);

            Location newLoc = this.dragon.getLocation().clone();
            newLoc.setDirection(velocity);
            this.dragon.teleport(newLoc);
        }

        public boolean isFinished() {
            return orbitTicks >= ORBIT_DURATION_TICKS && index >= spiralPath.size() * 0.8 &&
                    dragon.getLocation().distance(spiralPath.get(spiralPath.size() - 1)) <= 5;
        }

        public void debugParticles() {
            for (Location location : this.spiralPath) {
                location.getWorld().spawnParticle(Particle.SPELL_WITCH, location, 5, 0, 0, 0, 0);
            }
        }

        public List<Location> generateBezierSpiral(Location start, Location end, double maxRadius, int rotations, int points) {
            List<Location> path = new ArrayList<>();

            Vector mainDir = end.toVector().subtract(start.toVector());
            double totalLength = mainDir.length();
            mainDir.normalize();
            Vector perp = new Vector(-mainDir.getZ(), 0, mainDir.getX());

            for (int i = 0; i <= points; i++) {
                double t = (double) i / points;
                double radius = maxRadius;
                double angle = t * rotations * 2 * Math.PI;

                Vector offset = (t == 1) ? new Vector(0, 0, 0) :
                        perp.clone().multiply(Math.cos(angle))
                                .add(mainDir.clone().crossProduct(perp).multiply(Math.sin(angle)))
                                .normalize().multiply(radius);

                Vector along = mainDir.clone().multiply(t * totalLength);
                Vector control = start.toVector().add(along).add(offset);

                double y = start.getY() + t * (end.getY() - start.getY());
                path.add(new Location(start.getWorld(), control.getX(), y, control.getZ()));
            }

            return path;
        }
    }
}