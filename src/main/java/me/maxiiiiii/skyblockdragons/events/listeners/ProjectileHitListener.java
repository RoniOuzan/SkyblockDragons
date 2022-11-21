package me.maxiiiiii.skyblockdragons.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        e.getEntity().remove();
    }
}
