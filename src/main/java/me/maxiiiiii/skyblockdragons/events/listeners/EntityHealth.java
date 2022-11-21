package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityHealth implements Listener {
    public static final String SPLITTER = " " + ChatColor.GREEN + "" + ChatColor.YELLOW + "" + ChatColor.RED + "" + ChatColor.RESET + "";

    private void addHealth(LivingEntity entity) {
        String name = Functions.setTitleCase(entity.getCustomName());
        try {
            if (!entity.getCustomName().equals("")) {
                String [] customName = entity.getCustomName().split(SPLITTER);
                name = customName[0];
            }
        } catch (NullPointerException ignored) {
        }
        if (entity.getHealth() <= entity.getMaxHealth() / 4) {
            entity.setCustomName(name + SPLITTER + ChatColor.RED + Functions.getShortNumber(Math.ceil(entity.getHealth())) + StatTypes.HEALTH.getIcon());
        } else if (entity.getHealth() <= entity.getMaxHealth() / 2) {
            entity.setCustomName(name + SPLITTER + ChatColor.YELLOW + Functions.getShortNumber(Math.ceil(entity.getHealth())) + StatTypes.HEALTH.getIcon());
        } else {
            entity.setCustomName(name + SPLITTER + ChatColor.GREEN + Functions.getShortNumber(Math.ceil(entity.getHealth())) + StatTypes.HEALTH.getIcon());
        }
        entity.setCustomNameVisible(true);
    }

    public static String getName(EntitySD entity) {
        String name = Functions.setTitleCase(entity.material.getName());
        try {
            if (!entity.material.getName().equals("")) {
                String [] customName = entity.material.getName().split(SPLITTER);
                name = customName[0];
            }
        } catch (NullPointerException ignored) {
        }
        return name + SPLITTER + ChatColor.GREEN + Functions.getShortNumber(Math.ceil(entity.getHealth())) + StatTypes.HEALTH.getIcon();
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Creature) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    addHealth((LivingEntity) e.getEntity());
                }
            }.runTaskLater(SkyblockDragons.plugin, 1L);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Creature) {
            Functions.Wait(1L, () -> addHealth((LivingEntity) e.getEntity()));
        }
    }
}
