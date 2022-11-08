package me.maxiiiiii.skyblockdragons.player.listeners;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerDeathEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerDeathListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        PlayerSD player = e.getPlayer();

        player.teleport(player.getWorldSD().getWarp().getLocation());
        player.setHealth(player.getMaxHealth());

        player.getWorldSD().sendMessage(e.getMessage());
        player.setInvulnerable(true);
        Functions.Wait(1L, () -> player.spigot().respawn());
        Functions.Wait(100L, () -> player.setInvulnerable(false));
    }
}
