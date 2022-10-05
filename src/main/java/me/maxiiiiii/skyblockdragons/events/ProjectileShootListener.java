package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.tasks.AimingTask;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileShootListener implements Listener {
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getShooter() instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) projectile.getShooter());
            short enchantLevel = player.getEnchantLevel(EnchantType.AIMING);
            Item tool = player.getItems().getToolItem();
            String name = tool.getMaterial().name();
            if (name.equals("RUNAANS_BOW") || name.equals("YOUNG_BOW") || name.equals("STRONG_BOW")) {
                enchantLevel = 6;
            }
            else if (name.equals("HURRICANE_BOW")){
                enchantLevel = 0;
            }
            if (enchantLevel > 0){
//                player.sendMessage("Shot with aiming!");
                new AimingTask(projectile, enchantLevel).runTaskTimer(SkyblockDragons.plugin, 2L, 1L);
            }
        }
    }
}
