package me.maxiiiiii.skyblockdragons.worlds.griffin.listeners;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.worlds.griffin.events.PlayerDigBurrowEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDigBurrowListener implements Listener {
    @EventHandler
    public void onPlayerDigBurrow(PlayerDigBurrowEvent e) {
        PlayerSD player = e.getPlayer();
        player.sendMessage("dig!");

        player.getGriffin().next();
    }
}
