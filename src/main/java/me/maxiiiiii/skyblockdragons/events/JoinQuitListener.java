package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e)  {
        SkyblockDragons.logger.info("scam " + e.getPlayer().getName());

        Functions.Wait(1L, () -> {
            if (SkyblockDragons.getPlayer(e.getPlayer()) == null) {
                new PlayerSD(e.getPlayer());
            } else {
                SkyblockDragons.getPlayer(e.getPlayer()).update(e.getPlayer());
            }
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        if (player.getPlayerPet().activePet >= 0) {
            player.getPlayerPet().petArmorStand.armorStand.remove();
            player.getPlayerPet().petArmorStand.hologram.delete();
        }
        player.save();
//        SkyblockDragons.players.remove(e.getPlayer().getUniqueId());
    }
}
