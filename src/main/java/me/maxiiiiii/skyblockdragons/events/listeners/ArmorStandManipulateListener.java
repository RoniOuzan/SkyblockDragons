package me.maxiiiiii.skyblockdragons.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorStandManipulateListener implements Listener {
    @EventHandler
    public void onEquip(PlayerArmorStandManipulateEvent e) {
        if (!e.getRightClicked().isVisible()) e.setCancelled(true);
    }
}
