package me.maxiiiiii.skyblockdragons.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class PortalListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerEnterPortal(EntityPortalEvent e) {
        e.setCancelled(true);
    }
}
