package me.maxiiiiii.skyblockdragons.listeners;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.maxiiiiii.skyblockdragons.Functions.loadPlayerData;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Functions.Wait(1L, () -> loadPlayerData(e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        player.petArmorStand.armorStand.remove();
        player.petArmorStand.hologram.delete();
        SkyblockDragons.players.remove(e.getPlayer().getUniqueId());
    }
}
