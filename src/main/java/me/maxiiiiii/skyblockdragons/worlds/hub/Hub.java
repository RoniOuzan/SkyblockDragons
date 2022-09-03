package me.maxiiiiii.skyblockdragons.worlds.hub;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends WorldSD {
    public static final World world = Bukkit.getWorld("Hub");

    public Hub(JavaPlugin plugin) {
        super(world, "Hub", Warp.HUB, WorldType.HUB);
        plugin.getCommand("Hub").setExecutor(new HubCommand());
    }
}
