package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.itemcreator.Stat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityHealth implements Listener {
    private final String ADDER = " " + ChatColor.GREEN + "" + ChatColor.YELLOW + "" + ChatColor.RED + "" + ChatColor.RESET + "";

    private void addHealth(Entity entity) {
        String name = Functions.setTitleCase(entity.getType().toString().replaceAll("_", " "));
        try {
            if (!entity.getCustomName().equals("")) {
                String [] customName = entity.getCustomName().split(ADDER);
                name = customName[0];
            }
        } catch (NullPointerException ignored) {
        }
        if (((LivingEntity) entity).getHealth() <= ((LivingEntity) entity).getMaxHealth() / 4) {
            entity.setCustomName(name + " " + ChatColor.GREEN + "" + ChatColor.YELLOW + "" + ChatColor.RED + "" + ChatColor.RESET + "" + ChatColor.RED + Functions.getShortNumber(((LivingEntity) entity).getHealth()) + Stat.HEALTH.getIcon());
        } else if (((LivingEntity) entity).getHealth() <= ((LivingEntity) entity).getMaxHealth() / 2) {
            entity.setCustomName(name + " " + ChatColor.GREEN + "" + ChatColor.YELLOW + "" + ChatColor.RED + "" + ChatColor.RESET + "" + ChatColor.YELLOW + Functions.getShortNumber(((LivingEntity) entity).getHealth()) + Stat.HEALTH.getIcon());
        } else {
            entity.setCustomName(name + " " + ChatColor.GREEN + "" + ChatColor.YELLOW + "" + ChatColor.RED + "" + ChatColor.RESET + "" + ChatColor.GREEN + Functions.getShortNumber(((LivingEntity) entity).getHealth()) + Stat.HEALTH.getIcon());
        }
        entity.setCustomNameVisible(true);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Creature) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    addHealth(e.getEntity());
                }
            }.runTaskLater(SkyblockDragons.getInstance(), 1L);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Creature) {
            addHealth(e.getEntity());
        }
    }
}
