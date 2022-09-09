package me.maxiiiiii.skyblockdragons.player.chat.listeners;

import me.maxiiiiii.skyblockdragons.player.chat.events.PlayerGetMessageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerGetMessageListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerGetMessageListener(PlayerGetMessageEvent e) {
        if (!e.isCancelled()) {
            e.getPlayer().sendMessage(e.getMessage());
        }
    }
}
