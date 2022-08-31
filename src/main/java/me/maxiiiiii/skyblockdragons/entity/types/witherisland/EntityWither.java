package me.maxiiiiii.skyblockdragons.entity.types.witherisland;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import me.maxiiiiii.skyblockdragons.util.objects.FlyToLocation;
import me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.stream.Collectors;

public abstract class EntityWither extends EntityMaterial {

    public int phase = 0;
    public int i = 0;
    public UUID uuid = null;
    public EntitySD entitySD;
    public FlyToLocation flyToLocation;
    public String color = "§8";

    public EntityWither(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.WITHER, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, 0, 0);
    }

    public void strikeAbility(Entity entity) {
        this.strikeAbility(entity, 0.3);
    }

    protected void strikeAbility(Entity entity, double percent) {
        for (PlayerSD player : entity.getWorld().getPlayers().stream().map(SkyblockDragons::getPlayer).collect(Collectors.toList())) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                entity.getWorld().strikeLightningEffect(player.getLocation());
                player.makeDamage(entity, Damage.DamageType.TRUE, player.getHealthStat() * percent);
            }
        }
    }

    @Override
    public void onSpawn(EntitySD entity) {
        if (entity.entity instanceof Wither){
            WitherIsland.wither = this;
            WitherIsland.witherDamage.clear();
            uuid = entity.entity.getUniqueId();
            entitySD = entity;
            entity.entity.setMaximumNoDamageTicks(0);
            entity.entity.setNoDamageTicks(0);
            Wither witherBoss = (Wither) entity.entity;
            blueExplodeAbility(entity, 200);
        }
    }

    public void blueExplodeAbility(EntitySD entity, int value) {
        phase = 0;
        NBTEntity nbtEntity = new NBTEntity(entity.entity);
        nbtEntity.setInteger("Invul", value);
        Functions.Wait(value, () -> {
            phase = 1;
            entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 5, 7, 7, 7);
            for (Entity nearbyEntity : entity.getNearbyEntities(10, 10, 10)) {
                if (nearbyEntity instanceof Player){
                    Player player = (Player) nearbyEntity;
                    player.damage(1000000, entity.entity);
                }
            }
            i = 0;
        });
    }

    @Override
    public void onTick(EntitySD entity) {
        if (phase >= 1){

            //TODO: call abilities
            if (WitherIsland.getWitherTarget() != null){
                PlayerSD target = WitherIsland.getWitherTarget();
                if (i % (30 * 20) == 0){
                    skullRainAbility(entity, target);
                }
                if (i % (50 * 20) == 0){
                    superSkull(entity, target);
//                    superSkull.setDirection(superSkull.getDirection().multiply(0.5));
                }
            }

            if (phase == 1 && i % 80 == 0){
                moveAround(entity);
            }
        }
        i++;
    }

    public void superSkull(EntitySD entity, PlayerSD target) {
        flyToLocation.cancel();
        WitherSkull superSkull = shootSkullAtTarget(entity, target.getLocation());
        superSkull.setCharged(true);
    }

    public void skullRainAbility(EntitySD entity, PlayerSD target) {
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
        targetVector = targetVector.multiply(0.1);
        SkyblockDragons.logger.info(String.format("Shoot skull NERFED vel: %s", targetVector));
        WitherSkull witherSkull = entity.launchProjectile(WitherSkull.class, targetVector);
        witherSkull.setDirection(targetVector);
        SkyblockDragons.logger.info(String.format("Shot skull vel: %s", witherSkull.getDirection()));
        return witherSkull;
    }

    public void moveAround(EntitySD entity) {
        double x = Functions.randomDouble(-85, -40);
        double y = 71;
        if (entity.getHealth() > entity.getMaxHealth()/2)
            y = Functions.randomDouble(75, 85);
        else
            y = Functions.randomDouble(71, 75);
        double z = Functions.randomDouble(40, 80);
        Location location = new Location(entity.getWorld(), x, y, z);
        flyToLocation = new FlyToLocation(entity, location, 81, 1, true);
    }

    @Override
    public void onDamage(EntitySD attacker, EntitySD entity, Double damage) {
        if (attacker instanceof PlayerSD){
            PlayerSD playerSD = (PlayerSD) attacker;
            double oldDamage = WitherIsland.witherDamage.getOrDefault(attacker.getUniqueId(), 0d);
            double newDamage = oldDamage + damage;
            WitherIsland.witherDamage.put(attacker.getUniqueId(), newDamage);
            playerSD.sendMessage(String.format("Damage to Wither: %s", Functions.getNumberFormat(newDamage)));
        }
    }
}
