package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerAbilityLogger implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerUseAbilityTracker(PlayerUseAbilityEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player == null)
            return;
        String msg = "AbilityTracker: ability:  " + event.getAbility() + "item: " + event.getItem();
        player.getLogger().info(msg);
    }
}
