package me.maxiiiiii.skyblockdragons.world.worlds.griffin.listeners;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.events.PlayerDigBurrowEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDigBurrowListener implements Listener {
    @EventHandler
    public void onPlayerDigBurrow(PlayerDigBurrowEvent e) {
        PlayerSD player = e.getPlayer();
        player.sendMessage("dig!");

        player.getGriffin().next();

        Location dropLocation = e.getLocation().clone().add(0, 1, 0);
        double mobChance = 10 + (e.getPlayer().getItems().getTool().getMaterial().getRarity().getLevel() * 10);
        if (Functions.chanceOf(mobChance)) {

        }
    }
}
