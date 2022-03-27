package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class Parabola_Wand implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("PARABOLA_WAND")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        Location loc = player.getLocation();

        ArrayList<Location> locations = new ArrayList<>();

        for (double x = -20; x < 20; x += 0.2) {
            Location newLocation = loc.clone().add(x, Math.pow(x, 2), 0);
            locations.add(newLocation);
        }

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            locations.forEach(location -> {
                location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 200, 0, 0);
            });
        }, 0L, 1L);
    }
}
