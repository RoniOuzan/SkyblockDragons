package me.maxiiiiii.skyblockdragons.listeners;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.maxiiiiii.skyblockdragons.Functions.loadPlayerData;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Functions.Wait(1L, () -> loadPlayerData(e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        SkyblockDragons.players.remove(e.getPlayer().getUniqueId());
    }
}
