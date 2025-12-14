package me.maxiiiiii.skyblockdragons.entity.types.witherisland;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityAI;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.FlyToLocation;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class WitherAI extends EntityAI {
    private static final int TICKS_TO_SECONDS = 20;

    private final int tickRate;
    private final int moveRate;

    public int phase;
    private int i;

    public FlyToLocation flyToLocation;
    public Location middleLoc;

    public WitherAI(EntitySD entity, int tickRate, int moveRate) {
        super(entity, tickRate);
        this.tickRate = tickRate;
        this.moveRate = moveRate;
        this.phase = 0;
        this.i = 0;
    }

    @Override
    public void initialize() {
        blueExplodeAbility(200);
    }

    @Override
    public void run() {
        if (phase >= 1){
            try {
                if (getWitherTarget() != null){
                    PlayerSD target = getWitherTarget();
                    if (target != null) {
                        if (i % (80 * TICKS_TO_SECONDS) == 0) {
                            dashToPlayer(target);
                        } else if (i % (60 * TICKS_TO_SECONDS) == 0) {
                            skullEverywhere();
                        } else if (i % (50 * TICKS_TO_SECONDS) == 0) {
                            superSkull(target);
                        } else if (i % (30 * TICKS_TO_SECONDS) == 0) {
                            skullRainAbility(target);
                        }
                    }
                }

                if (phase == 1 && i % moveRate == 0){
                    moveAround();
                }
            } catch (Exception e){
                phase = 1;
                e.printStackTrace();
            }
        }
        i += tickRate;
    }

    public PlayerSD getWitherTarget() {
        List<UUID> sortedWitherDamage = WorldSD.WITHER_ISLAND.sortedWitherDamage();
        for (UUID uuid : sortedWitherDamage) {
            PlayerSD target = SkyblockDragons.getPlayer(uuid);
            if (target != null && target.isOnline()) {
                Location location = this.entity.getLocation();
                if (location.getWorld().equals(target.getWorld()) && location.distance(target.getLocation()) <= 70) {
                    return target;
                }
            }
        }
        return null;
    }

    public void blueExplodeAbility(int value) {
        phase = 0;
        setInvul(value);
        Functions.Wait(value, () -> {
            phase = 1;
            this.entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, this.entity.getLocation(), 1, 5, 5, 5);
            damageEntitiesRadius(10, 1000000);
            i = 0;
        });
    }

    public void damageEntitiesRadius(int radius, double damage) {
        for (Entity nearbyEntity : this.entity.getNearbyEntities(radius, radius, radius)) {
            if (nearbyEntity instanceof Player){
                Player player = (Player) nearbyEntity;
                player.damage(damage, this.entity.entity);

                pushAway(player, 3);

//                pushBackwards(player);
            }
        }
    }

    public void pushAway(Player player, int m) {
        Vector vector = player.getLocation().subtract(this.entity.getLocation()).toVector().normalize();
        vector.multiply(m);
        vector.setY(1);
        player.setVelocity(vector);
    }

    public void pushBackwards(Player player) {
        Vector vector = player.getLocation().getDirection();
        vector.multiply(-1);
        vector.setY(1);
        player.setVelocity(vector);
    }

    public void setInvul(int value) {
        NBTEntity nbtEntity = new NBTEntity(entity.entity);
        nbtEntity.setInteger("Invul", value);
    }

    public void skullEverywhere() {
        phase = 2;
        flyToLocation.cancel();
        middleLoc = new Location(this.entity.getWorld(), -64, 75, 63);
        playSound(Sound.ENTITY_WITHER_DEATH, 50F, 2F);
        moveToLoc(middleLoc, 20, 0);
        Functions.Wait(20, () -> {
            moveToLoc(middleLoc, 80, 0);
            skullEverywhereNow();
        });
    }

    public void skullEverywhereNow(){
        AtomicReference<Double> j = new AtomicReference<>((double) 0);
        Functions.Loop(36 * 2, 1, amount -> {
            double y = -0.4;
            if (amount >= 36){
                y = -0.5;
            }
            shootSkullAtDirection(new Vector(Math.sin(j.get()), y, Math.cos(j.get())));
            j.updateAndGet(v -> (v + (Math.PI * 2) / 36));
        }, amount -> phase = 1);
    }

    public void dashToPlayer(PlayerSD target) {
        phase = 2;
        flyToLocation.cancel();
        Location current = this.entity.getLocation().clone();
        moveToLoc(current, 10 * 5, 0);
        AtomicReference<Float> pitch = new AtomicReference<>(1F);
        Functions.Loop(10, 5, amount -> {
            playSound(Sound.ENTITY_WITHER_DEATH, 50F, pitch.get());
            pitch.updateAndGet(v -> v + 0.1F);
        }, amount -> dashToPlayerNow(target));
    }

    public void dashToPlayerNow(PlayerSD target){
        Location targetLocation = target.getLocation();
        moveToLoc(targetLocation, 40, 0);
        Functions.Loop(40, 1, amount -> {
            entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 1, 2, 2, 2);
            damageEntitiesRadius(5, this.entity.getMaterial().getDamage() * 4);
            playSound(Sound.ENTITY_WITHER_BREAK_BLOCK, 1F,1F);
        }, amount -> phase = 1);
    }

    public void superSkull(PlayerSD target) {
        playSound(Sound.ENTITY_WITHER_SHOOT, 50F, 0.6F);
        flyToLocation.cancel();
        WitherSkull superSkull = shootSkullAtTarget(target.getLocation());
        superSkull.setCharged(true);
    }

    public void skullRainAbility(PlayerSD target) {
        playSound(Sound.ENTITY_WITHER_SPAWN, 50F, 2F);
        phase = 2;
        flyToLocation.cancel();
        entity.entity.setAI(false);
        Functions.Loop(10, 2, i -> shootSkullAtTarget(target.getLocation()), i -> {
            phase = 1;
            entity.entity.setAI(true);
        });
    }

    public WitherSkull shootSkullAtTarget(Location target) {
        Location entityLocation = entity.getLocation();
        if (target.getWorld() != entityLocation.getWorld()) return null;
        Vector targetVector = target.subtract(entityLocation).toVector().normalize();
        SkyblockDragons.logger.info(String.format("Shoot skull vel: %s", targetVector));
        targetVector = targetVector.multiply(0.1).clone();
        SkyblockDragons.logger.info(String.format("Shoot skull NERFED vel: %s", targetVector));
        WitherSkull witherSkull = shootSkullAtDirection(targetVector);
        SkyblockDragons.logger.info(String.format("Shot skull vel: %s", witherSkull.getDirection()));
        return witherSkull;
    }

    @NotNull
    public WitherSkull shootSkullAtDirection(Vector targetVector) {
        WitherSkull witherSkull = this.entity.launchProjectile(WitherSkull.class);
        witherSkull.setVelocity(targetVector);
        witherSkull.setDirection(targetVector);
        return witherSkull;
    }

    public void moveAround() {
        double x = Functions.randomDouble(-85, -40);
        double y;
        if (this.entity.getHealth() > this.entity.getMaxHealth()/2)
            y = Functions.randomDouble(75, 85);
        else
            y = Functions.randomDouble(71, 75);
        double z = Functions.randomDouble(40, 80);
        moveToLoc(x, y, z, moveRate + 1, 1);
    }

    public void moveToLoc(double x, double y, double z, long ticks, double stopAt) {
        Location location = new Location(this.entity.getWorld(), x, y, z);
        moveToLoc(location, ticks, stopAt);
    }

    public void moveToLoc(Location location, long ticks, double stopAt) {
        flyToLocation = new FlyToLocation(this.entity, location, ticks, stopAt, true);
    }

    public void playSound(Sound sound, float volume, float pitch){
        this.entity.getWorld().playSound(this.entity.getLocation(), sound, volume, pitch);
    }
}
