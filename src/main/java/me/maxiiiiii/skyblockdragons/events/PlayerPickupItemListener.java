package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {
    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e) {
        PlayerGetItemEvent event = new PlayerGetItemEvent(SkyblockDragons.getPlayer(e.getPlayer()), e.getItem().getItemStack());
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
