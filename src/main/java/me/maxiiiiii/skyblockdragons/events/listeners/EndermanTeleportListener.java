package me.maxiiiiii.skyblockdragons.events.listeners;

import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

public class EndermanTeleportListener implements Listener {
    @EventHandler
    public void onEndermanTeleport(EntityTeleportEvent e) {
        if (e.getEntity() instanceof Enderman) {
            e.setCancelled(true);
        }
    }
}
