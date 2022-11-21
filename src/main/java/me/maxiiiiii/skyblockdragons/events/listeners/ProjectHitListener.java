package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.events.events.ProjectileHitEvent;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectHitListener implements Listener {
    private static final String TAG = "ShooterItemMaterial";

    @EventHandler
    public void onProjectHit(org.bukkit.event.entity.ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getEntity().getScoreboardTags().stream().anyMatch(t -> t.contains(TAG))) {
            Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(
                    SkyblockDragons.getPlayer((Player) e.getEntity().getShooter()),
                    e.getEntity(),
                    Items.get(e.getEntity().getScoreboardTags().stream().filter(t -> t.contains(TAG)).findFirst().orElse("").replace(TAG + ":", "")),
                    e.getHitEntity(),
                    e.getHitBlock()
            ));
        }
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            e.getEntity().addScoreboardTag(TAG + ":" + Items.get(((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand()).name());
        }
    }
}
