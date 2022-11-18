package me.maxiiiiii.skyblockdragons.player.listeners;

import me.maxiiiiii.skyblockdragons.player.events.PlayerGetCoinsEvent;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetCoinsFromEntityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerGetCoinsListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetCoins(PlayerGetCoinsEvent e) {
        e.getPlayer().giveCoins(e.getMultiplier().multiply(e.getAmount()));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetCoinsFromEntity(PlayerGetCoinsFromEntityEvent e) {
        Functions.createHologram(e.getEntity().getLocation().add(0, 3, 0), new ArrayList<>(Arrays.asList(ChatColor.GOLD + e.getPlayer().getName(), ChatColor.GOLD + "+" + Functions.getNumberFormat(e.getEntity().material.getCoins()))), 40);
    }
}
