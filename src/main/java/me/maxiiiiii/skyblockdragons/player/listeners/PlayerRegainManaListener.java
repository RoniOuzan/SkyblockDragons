package me.maxiiiiii.skyblockdragons.player.listeners;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.events.PlayerRegainManaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerRegainManaListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerRegainMana(PlayerRegainManaEvent e) {
        e.getPlayer().getStats().add(StatTypes.MANA, e.getMultiplier().multiply(e.getAmount()));
    }
}
