package me.maxiiiiii.skyblockdragons.entity.types.witherisland;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import me.maxiiiiii.skyblockdragons.util.objects.FlyToLocation;
import me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public abstract class EntityWither extends EntityMaterial {

    public static final int TICKS_TO_SECONDS = 20;
    public int moveRate = 80;
    public int phase = 0;
    public int i = 0;
    public UUID uuid = null;
    public EntitySD entitySD;
    public FlyToLocation flyToLocation;
    public String color = "§8";
    public ItemMaterial crystal = null;
    public int tickRate = 1;

    public Location middleLoc;

    public EntityWither(String name, double health, double damage, double trueDamage, int moveRate, String color, int tickRate) {
        super(EntityType.WITHER, name, -1, health, 100, damage, trueDamage, new Equipment(), 100, 1, true, 0, 0);
        this.moveRate = moveRate;
        this.color = color;
        this.tickRate = tickRate;
    }

    public EntityWither(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed) {
        this(name, level, health, defense, damage, trueDamage, equipment, speed, 1);

    }

    public EntityWither(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.WITHER, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, 0, 0);
    }

    @Override
    public void onSpawn(EntitySD entity) {
        if (entity.entity instanceof Wither){
            WitherIsland.wither = entity;
            WitherIsland.witherDamage.clear();
            uuid = entity.entity.getUniqueId();
            entitySD = entity;
            entity.entity.setMaximumNoDamageTicks(0);
            entity.entity.setNoDamageTicks(0);
            entity.entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
            blueExplodeAbility(entity, 200);
        }
    }

    public void blueExplodeAbility(EntitySD entity, int value) {
        phase = 0;
        setInvul(entity, value);
        Functions.Wait(value, () -> {
            phase = 1;
            entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 1, 5, 5, 5);
            damageEntitiesRadius(entity, 10, 1000000);
            i = 0;
        });
    }

    public void damageEntitiesRadius(EntitySD entity, int radius, double damage) {
        for (Entity nearbyEntity : entity.getNearbyEntities(radius, radius, radius)) {
            if (nearbyEntity instanceof Player){
                Player player = (Player) nearbyEntity;
                player.damage(damage, entity.entity);

                pushAway(entity, player, 3);

//                pushBackwards(player);
            }
        }
    }

    public void pushAway(EntitySD entity, Player player, int m) {
        Vector vector = player.getLocation().subtract(entity.getLocation()).toVector().normalize();
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

    public void setInvul(EntitySD entity, int value) {
        NBTEntity nbtEntity = new NBTEntity(entity.entity);
        nbtEntity.setInteger("Invul", value);
    }

    @Override
    public void onTick(EntitySD entity) {
        if (phase >= 1){
            try {
                if (WitherIsland.getWitherTarget() != null){
                    PlayerSD target = WitherIsland.getWitherTarget();
                    if (target != null) {
                        if (i % (80 * TICKS_TO_SECONDS) == 0) {
                            dashToPlayer(entity, target);
                        } else if (i % (60 * TICKS_TO_SECONDS) == 0) {
                            skullEverywhere(entity);
                        } else if (i % (50 * TICKS_TO_SECONDS) == 0) {
                            superSkull(entity, target);
                        } else if (i % (30 * TICKS_TO_SECONDS) == 0) {
                            skullRainAbility(entity, target);
                        }
                    }
                }

                if (phase == 1 && i % moveRate == 0){
                    moveAround(entity);
                }
            } catch (Exception e){
                phase = 1;
                e.printStackTrace();
            }
        }
        i += tickRate;
    }

    public void skullEverywhere(EntitySD entity) {
        phase = 2;
        flyToLocation.cancel();
        middleLoc = new Location(entity.getWorld(), -64, 75, 63);
        playSound(entity, Sound.ENTITY_WITHER_DEATH, 50F, 2F);
        moveToLoc(entity, middleLoc, 20, 0);
        Functions.Wait(20, () -> {
            moveToLoc(entity, middleLoc, 80, 0);
            skullEverywhereNow(entity);
        });
    }

    public void skullEverywhereNow(EntitySD entity){
        AtomicReference<Double> j = new AtomicReference<>((double) 0);
        Functions.Loop(36 * 2, 1, amount -> {
            double y = -0.4;
            if (amount >= 36){
                y = -0.5;
            }
            shootSkullAtDirection(entity, new Vector(Math.sin(j.get()), y, Math.cos(j.get())));
            j.updateAndGet(v -> (v + (Math.PI * 2) / 36));
        }, amount -> phase = 1);
    }

    public void dashToPlayer(EntitySD entity, PlayerSD target) {
        phase = 2;
        flyToLocation.cancel();
        Location current = entity.getLocation().clone();
        moveToLoc(entity, current, 10 * 5, 0);
        AtomicReference<Float> pitch = new AtomicReference<>(1F);
        Functions.Loop(10, 5, amount -> {
            playSound(entity, Sound.ENTITY_WITHER_DEATH, 50F, pitch.get());
            pitch.updateAndGet(v -> v + 0.1F);
        }, amount -> dashToPlayerNow(entity, target));
    }

    public void dashToPlayerNow(EntitySD entity, PlayerSD target){
        Location targetLocation = target.getLocation();
        moveToLoc(entity, targetLocation, 40, 0);
        Functions.Loop(40, 1, amount -> {
            entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 1, 2, 2, 2);
            damageEntitiesRadius(entity, 5, damage * 4);
            playSound(entity, Sound.ENTITY_WITHER_BREAK_BLOCK, 1F,1F);
        }, amount -> phase = 1);
    }

    public void superSkull(EntitySD entity, PlayerSD target) {
        playSound(entity, Sound.ENTITY_WITHER_SHOOT, 50F, 0.6F);
        flyToLocation.cancel();
        WitherSkull superSkull = shootSkullAtTarget(entity, target.getLocation());
        superSkull.setCharged(true);
    }

    public void skullRainAbility(EntitySD entity, PlayerSD target) {
        playSound(entity, Sound.ENTITY_WITHER_SPAWN, 50F, 2F);
        phase = 2;
        flyToLocation.cancel();
        entity.entity.setAI(false);
        Functions.Loop(10, 2, amount -> {
            shootSkullAtTarget(entity, target.getLocation());
        }, amount -> {
            phase = 1;
            entity.entity.setAI(true);
        });
    }

    public WitherSkull shootSkullAtTarget(EntitySD entity, Location target) {
        Location entityLocation = entity.getLocation();
        if (target.getWorld() != entityLocation.getWorld()) return null;
        Vector targetVector = target.subtract(entityLocation).toVector().normalize();
        SkyblockDragons.logger.info(String.format("Shoot skull vel: %s", targetVector));
        targetVector = targetVector.multiply(0.1).clone();
        SkyblockDragons.logger.info(String.format("Shoot skull NERFED vel: %s", targetVector));
        WitherSkull witherSkull = shootSkullAtDirection(entity, targetVector);
        SkyblockDragons.logger.info(String.format("Shot skull vel: %s", witherSkull.getDirection()));
        return witherSkull;
    }

    @NotNull
    public WitherSkull shootSkullAtDirection(EntitySD entity, Vector targetVector) {
        WitherSkull witherSkull = entity.launchProjectile(WitherSkull.class);
        witherSkull.setVelocity(targetVector);
        witherSkull.setDirection(targetVector);
        return witherSkull;
    }

    public void moveAround(EntitySD entity) {
        double x = Functions.randomDouble(-85, -40);
        double y;
        if (entity.getHealth() > entity.getMaxHealth()/2)
            y = Functions.randomDouble(75, 85);
        else
            y = Functions.randomDouble(71, 75);
        double z = Functions.randomDouble(40, 80);
        moveToLoc(entity, x, y, z, moveRate + 1, 1);
    }

    public void moveToLoc(EntitySD entity, double x, double y, double z, long ticks, double stopAt) {
        Location location = new Location(entity.getWorld(), x, y, z);
        moveToLoc(entity, location, ticks, stopAt);
    }

    public void moveToLoc(EntitySD entity, Location location, long ticks, double stopAt) {
        flyToLocation = new FlyToLocation(entity, location, ticks, stopAt, true);
    }

    public void playSound(EntitySD entity, Sound sound, float volume, float pitch){
        entity.getWorld().playSound(entity.getLocation(), sound, volume, pitch);
    }

    @Override
    public void onDamage(EntitySD attacker, EntitySD entity, Double damage) {
        if (attacker instanceof PlayerSD){
            PlayerSD playerSD = (PlayerSD) attacker;
            double oldDamage = WitherIsland.witherDamage.getOrDefault(playerSD.getUniqueId(), 0d);
            double newDamage = oldDamage + damage;
            WitherIsland.witherDamage.put(playerSD.getUniqueId(), newDamage);
//            playerSD.sendMessage(String.format("Damage to Wither: %s", Functions.getNumberFormat(newDamage)));
        }
    }

    /*
    wither guide:
    Athena = Intelligence = execute more abilities
    Phanes = Health = more health
    Hermes = Speed = moves faster
    Demeter = Crit damage = deals more damage
    Ares = Strength = deals more damage
     */
}
