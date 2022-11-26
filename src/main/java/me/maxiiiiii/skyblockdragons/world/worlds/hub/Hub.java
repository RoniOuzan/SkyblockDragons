package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends WorldSD {
    public static final World world = Bukkit.getWorld("Hub");

    public Hub(JavaPlugin plugin) {
        super(world, "Hub", Warp.HUB, WorldType.HUB, WorldType.COMBAT, WorldType.MINING);

        plugin.getCommand("Hub").setExecutor(new HubCommand());
    }

    @Override
    protected void spawnNPCs() {
        new WarpNPC(new Location(world, 4.5, 70, -94.5));

        new ReforgeNPC(new Location(world, -19.5, 71, -123.5, -90, 0));
    }
}
