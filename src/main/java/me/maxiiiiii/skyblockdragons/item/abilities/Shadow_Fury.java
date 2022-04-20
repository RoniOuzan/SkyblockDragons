package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class Shadow_Fury implements Listener {
    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("SHADOW_FURY")) return;

        PlayerSD player = e.getPlayer();

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
