package me.maxiiiiii.skyblockdragons.worlds.bearisland;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BearIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("BearIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, 0, 0, 0);
    public static final Location BEAR_SPAWN = new Location(world, 0, 0, 0);
    public static final Location MIDDLE = new Location(world, 0, 0, 0);
    public static EntitySD bear = null;

    public static Map<UUID, Double> bearDamage = new HashMap<>();

    public BearIsland(JavaPlugin plugin) {
        super(world, "Bear Island", Warp.BEAR_ISLAND, WorldType.COMBAT);
    }
}
