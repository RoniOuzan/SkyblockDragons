package me.maxiiiiii.skyblockdragons.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class PlayerRegenCanceller implements Listener {
    @EventHandler
    public void onRegen(EntityRegainHealthEvent e) {
        e.setCancelled(true);
    }
}
