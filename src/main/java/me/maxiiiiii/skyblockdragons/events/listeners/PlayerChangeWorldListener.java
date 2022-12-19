package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorldListener implements Listener {
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        player.getWorld().setGameRuleValue("naturalRegeneration", "false");

        player.updatePlayerInventory();
        Functions.Wait(20L, () -> {
            if (player.getGameMode() != GameMode.CREATIVE){
                player.setAllowFlight(false);
            }
        });
    }
}
