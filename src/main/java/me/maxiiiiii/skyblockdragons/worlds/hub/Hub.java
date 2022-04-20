package me.maxiiiiii.skyblockdragons.worlds.hub;

import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends WorldSD {
    public Hub(JavaPlugin plugin) {
        super(Bukkit.getWorld("Hub"), "Hub", WorldType.HUB);
        plugin.getCommand("Hub").setExecutor(new HubCommand());
    }
}
