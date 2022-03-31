package me.maxiiiiii.skyblockdragons.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickUpListeners implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        e.getItemDrop().addScoreboardTag(e.getPlayer().getName());
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        if (!e.getItem().getScoreboardTags().contains(e.getPlayer().getName())) e.setCancelled(true);
    }
}
