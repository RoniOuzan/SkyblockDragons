package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.Functions;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.Functions.*;
import static me.maxiiiiii.skyblockdragons.Functions.getId;

public class ERRORMerang_Wand implements Listener {
    private final int blocks = 50;
    private final double degrees = 20;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("ERRORMERANG_WAND")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        Location location = player.getLocation().add(0, 1, 0);
        player.sendMessage("x: " + location.getDirection().getX());
        player.sendMessage("y: " + location.getDirection().getY());
        player.sendMessage("z: " + location.getDirection().getZ());
        player.sendMessage("-------------------------------------");

        Functions.playCircle(player, Particle.SPELL, 3, 1L);
    }
}
