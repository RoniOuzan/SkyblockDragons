package me.maxiiiiii.skyblockdragons.worlds.hub;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends WorldSD {
    public Hub(JavaPlugin plugin) {
        super(Bukkit.getWorld("Hub"), "Hub", WorldType.HUB);
        plugin.getCommand("Hub").setExecutor(new HubCommand());
    }
}
