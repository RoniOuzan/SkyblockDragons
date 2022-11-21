package me.maxiiiiii.skyblockdragons.player.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetExperienceEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerGetExperienceListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetExperience(PlayerGetExperienceEvent e) {
        e.getPlayer().giveExp((int) e.getMultiplier().multiply(e.getBaseAmount()));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetVanillaExperience(PlayerExpChangeEvent e) {
        Bukkit.getPluginManager().callEvent(new PlayerGetExperienceEvent(SkyblockDragons.getPlayer(e.getPlayer()), e.getAmount()));
        e.setAmount(0);
    }
}
