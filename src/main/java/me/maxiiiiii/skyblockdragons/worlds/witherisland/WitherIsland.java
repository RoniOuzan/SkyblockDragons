package me.maxiiiiii.skyblockdragons.worlds.witherisland;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class WitherIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("WitherIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, -63.500, 72.50000, 63.500);
    public static final Location WITHER_SPAWN = new Location(world, -63.500, 72.50000, 63.500);
    public static final Location MIDDLE = new Location(world, -63.500, 72.50000, 63.500);
    public static EntitySD Wither = null;

    public  static final Map<PlayerSD, Double> witherDamage = new HashMap<>();

    public WitherIsland(JavaPlugin plugin) {
        super(world, "Wither Island", new Location(world, -109.500,65.00000, 139.500), WorldType.COMBAT, WorldType.MINING);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if (e.getLocation().getWorld() != world) return;
        if (e.getEntity() instanceof org.bukkit.entity.Wither) {
        org.bukkit.entity.Wither Wither = (org.bukkit.entity.Wither) e.getEntity();

        }
    }
}
