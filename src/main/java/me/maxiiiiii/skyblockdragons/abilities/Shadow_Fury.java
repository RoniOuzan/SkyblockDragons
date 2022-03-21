package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.Functions.*;
import static me.maxiiiiii.skyblockdragons.Functions.cooldown;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class Shadow_Fury implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("SHADOW_FURY")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 15000, true)) return;

        List<Entity> nearbyEntities = player.getNearbyEntities(10, 10, 10);
        ArrayList<Entity> entities = new ArrayList<>();
        for (Entity entity : nearbyEntities) {
            if (entity instanceof Creature) {
                entities.add(entity);
            }
        }
        if (entities.size() == 0) return;

        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i >= 5) return;
                Entity nearestEntity = entities.get(0);
                for (Entity entity : entities) {
                    if (player.getLocation().distance(entity.getLocation()) < player.getLocation().distance(nearestEntity.getLocation())) {
                        nearestEntity = entity;
                    }
                }
                Location l = nearestEntity.getLocation().add(nearestEntity.getLocation().getDirection().multiply(-1));
                l.setY(nearestEntity.getLocation().getY() + 0.2);
                player.teleport(l);
                entities.remove(nearestEntity);
                if (entities.size() == 0) return;
                i++;
            }
        }.runTaskTimer(plugin, 0L, 10L);
    }
}
