package me.maxiiiiii.skyblockdragons.worlds.InfintyIsland;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;

import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.worlds.end.listeners.DragonKillListener;
import me.maxiiiiii.skyblockdragons.worlds.end.listeners.PlayerPlaceEyeListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class InfintyIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("InfintyIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, 0, 0, 0);
    public static final Location DRAGON_SPAWN = new Location(world, 0, 0, 0);
    public static final Location MIDDLE = new Location(world, 0, 0, 0);
    public static final Map<PlayerSD, Double> dragonDamage = new HashMap<>();
    public static EntitySD dragon = null;

    public InfintyIsland(JavaPlugin plugin) {
        super(world, "Infinty Island", new Location(world, 0, 0, 0, 90, 0), WorldType.COMBAT, WorldType.MINING);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerPlaceEyeListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DragonKillListener(), plugin);
    }
}
