package me.maxiiiiii.skyblockdragons.player.listeners;

import me.maxiiiiii.skyblockdragons.player.events.PlayerRegainHealthEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerRegainHealthListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerRegain(PlayerRegainHealthEvent e) {
        e.getPlayer().heal(e.applyMultiplier() * (1 + (e.getPlayer().getStats().getVitality().get() / 100)));
    }
}
