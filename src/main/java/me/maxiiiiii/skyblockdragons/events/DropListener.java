package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (Functions.getId(e.getItemDrop().getItemStack()).equals("SKYBLOCK_MENU")) e.setCancelled(true);
    }
}
