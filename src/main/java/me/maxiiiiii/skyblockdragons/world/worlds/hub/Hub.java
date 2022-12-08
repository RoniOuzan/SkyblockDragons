package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.world.LaunchPad;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class Hub extends WorldSD {
    public static final World world = Bukkit.getWorld("Hub");

    public Hub(JavaPlugin plugin) {
        super(world, "Hub", Warp.HUB, WorldType.HUB, WorldType.COMBAT, WorldType.MINING);
        addLaunchPad(new LaunchPad(new Location(world, -9.5, 64, -229.5), 5, Warp.DEEP_MINES));

        plugin.getCommand("Hub").setExecutor(new HubCommand());
    }

    @Override
    protected void spawnNPCs() {
        new WarpNPC(new Location(world, 4.5, 70, -94.5));
        new DailyNPC(new Location(world, -18.5, 70, -90, -90, 0));
        new ShopNPC(new Location(world, -18.5, 70, -77, -90, 0));
        new ReforgeNPC(new Location(world, -19.5, 71, -123.5, -90, 0));
        new AuctionMasterNPC(new Location(world, 17.5, 71, -78.5, 90, 0));
    }

    public static Hub deserialize(Map<String, Object> args) {
        return WorldSD.HUB;
    }
}
