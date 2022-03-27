package me.maxiiiiii.skyblockdragons.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class OnSlotChange implements Listener {
    @EventHandler
    public void onSlotChange(PlayerChangedMainHandEvent e) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            players.get(e.getPlayer().getUniqueId()).applyStats(false);
        }, 1L);
    }
}
