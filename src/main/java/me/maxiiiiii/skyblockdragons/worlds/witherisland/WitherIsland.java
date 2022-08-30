package me.maxiiiiii.skyblockdragons.worlds.witherisland;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class WitherIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("WitherIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, -63.500, 72.50000, 63.500);
    public static final Location WITHER_SPAWN = new Location(world, -63.500, 72.50000, 63.500);
    public static final Location MIDDLE = new Location(world, -63.500, 72.50000, 63.500);
    public static EntityWither wither = null;

    public static final Map<UUID, Double> witherDamage = new HashMap<>();

    public WitherIsland(JavaPlugin plugin) {
        super(world, "Wither Island", new Location(world, -109.500,65.00000, 139.500), WorldType.COMBAT, WorldType.MINING);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if (e.getLocation().getWorld() != world) return;
        if (e.getEntity() instanceof Wither) {
            Wither Wither = (Wither) e.getEntity();

        }
    }

    @EventHandler
    public void onWitherSkullHit(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        LivingEntity shooter = (LivingEntity) projectile.getShooter();
        if (shooter.getUniqueId().equals(wither.uuid)){
            for (Entity entity : projectile.getNearbyEntities(3, 3, 3)) {
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    player.damage(500, wither.entitySD.entity);
                }
            }
        }
    }

    public static List<UUID> sortedWitherDamage(){
        return new ArrayList<>(sortedWitherDamageMap().keySet());
    }

    @NotNull
    public static Map<UUID, Double> sortedWitherDamageMap() {
        return Functions.sortByValue(witherDamage);
    }

    public UUID getLeaderBoard(int index){
        return sortedWitherDamage().get(index);
    }

}
