package me.maxiiiiii.skyblockdragons.player.chat.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.chat.ChatChannel;
import me.maxiiiiii.skyblockdragons.player.chat.events.PlayerSendMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerSendMessage(AsyncPlayerChatEvent e) {
        if (!e.isCancelled()) {
            e.setCancelled(true);
            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            if (!player.getChatChannel().getCondition().check(player)) {
                player.sendMessage(ChatColor.RED + "You moved to chat channel ALL because " + player.getChatChannel().getMessageElse());
                player.setChatChannel(ChatChannel.ALL);
                return;
            }

            Bukkit.getPluginManager().callEvent(new PlayerSendMessageEvent(player, e.getMessage(), player.getChatChannel()));
        }
    }
}
