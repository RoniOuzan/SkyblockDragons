package me.maxiiiiii.skyblockdragons.worlds.griffin;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import me.maxiiiiii.skyblockdragons.worlds.griffin.events.PlayerDigBurrowEvent;
import me.maxiiiiii.skyblockdragons.worlds.griffin.listeners.PlayerDigBurrowListener;
import me.maxiiiiii.skyblockdragons.worlds.mining.PlayerBreakBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GriffinIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("GriffinIsland");

    public GriffinIsland(JavaPlugin plugin) {
        super(world, "Griffin Island", WorldType.COMBAT, WorldType.MINING);
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

    @EventHandler
    public void onPlayerWorldChange(PlayerChangedWorldEvent e) {
        if (e.getPlayer().getWorld() == world) {
            SkyblockDragons.getPlayer(e.getPlayer()).getGriffin().revealBlock();
        }
    }
}
