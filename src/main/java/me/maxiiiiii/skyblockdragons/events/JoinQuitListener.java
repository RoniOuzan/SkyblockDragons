package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.storage.Variables;
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
        Functions.Wait(1L, () -> new PlayerSD(e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        if (player.getPlayerPet().activePet >= 0) {
            player.getPlayerPet().petArmorStand.armorStand.remove();
            player.getPlayerPet().petArmorStand.hologram.delete();
        }
        player.save();
        SkyblockDragons.players.remove(e.getPlayer().getUniqueId());
    }
}
