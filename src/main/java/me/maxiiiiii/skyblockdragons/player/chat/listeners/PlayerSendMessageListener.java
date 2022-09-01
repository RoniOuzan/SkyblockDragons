package me.maxiiiiii.skyblockdragons.player.chat.listeners;

import me.maxiiiiii.skyblockdragons.player.chat.events.PlayerSendMessageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerSendMessageListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerSendMessage(PlayerSendMessageEvent e) {
        if (!e.isCancelled()) {
            e.getPlayer().makePlayerSay(e.getMessage());
        }
    }
}
