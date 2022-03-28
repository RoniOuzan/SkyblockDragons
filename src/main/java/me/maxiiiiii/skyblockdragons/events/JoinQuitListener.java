package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Functions.Wait(5L, () -> PlayerSD.loadPlayerData(e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        if (player.activePet >= 0)
            player.petArmorStand.armorStand.remove();
        SkyblockDragons.players.remove(e.getPlayer().getUniqueId());
    }
}
