package me.maxiiiiii.skyblockdragons.world.worlds.griffin;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.mining.events.PlayerBreakBlockEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.events.PlayerDigBurrowEvent;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.listeners.PlayerDigBurrowListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class GriffinIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("GriffinIsland");

    public GriffinIsland(JavaPlugin plugin) {
        super(world, "Griffin Island", Warp.GRIFFIN_ISLAND, WorldType.COMBAT, WorldType.MINING);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerDigBurrowListener(), plugin);
    }

    @EventHandler
    public void onPlayerBreakBlock(PlayerBreakBlockEvent e) {
        if (e.getPlayer().getWorld() != world) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        if (player.getGriffin().isBurrow(e.getBlock().getLocation())) {
            PlayerDigBurrowEvent event = new PlayerDigBurrowEvent(player);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public static GriffinIsland deserialize(Map<String, Object> args) {
        return WorldSD.GRIFFIN_ISLAND;
    }
}
