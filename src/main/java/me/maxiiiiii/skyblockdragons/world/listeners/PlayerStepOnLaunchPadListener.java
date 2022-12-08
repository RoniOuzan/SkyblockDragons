package me.maxiiiiii.skyblockdragons.world.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.world.events.PlayerStepOnLaunchPadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerStepOnLaunchPadListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerStepOnLaunchPad(PlayerStepOnLaunchPadEvent e) {
        SkyblockDragons.getPlayer(e.getPlayer()).warp(e.getLaunchPad().getWarpTo());
    }
}
