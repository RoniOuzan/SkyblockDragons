package me.maxiiiiii.skyblockdragons.world.listeners;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.events.PlayerVisitNewWorldEvent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerVisitNewWorldListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerVisitNewWorld(PlayerVisitNewWorldEvent e) {
        PlayerSD player = e.getPlayer();

        player.sendMessage(ChatColor.GREEN + "You have visit " + e.getWorld().getName() + " for the first time.");
        player.sendMessage(ChatColor.GREEN + "Now you can travel to this world whenever you are with /warp.");
        Functions.Wait(20, () -> {
            player.playSound(Sound.ENTITY_PLAYER_LEVELUP, 1f, 0f);
            player.sendTitle(ChatColor.GOLD + "New World", ChatColor.AQUA + e.getWorld().getName(), 10, 80, 5);
        });
    }
}
