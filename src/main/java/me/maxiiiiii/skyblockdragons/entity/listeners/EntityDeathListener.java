package me.maxiiiiii.skyblockdragons.entity.listeners;

import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class EntityDeathListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent e) {
        e.getEntity().kill();
    }
}
