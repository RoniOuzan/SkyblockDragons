package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

import static me.maxiiiiii.skyblockdragons.util.Functions.cooldown;
public class ERROR_SCYTHE implements Listener {
    private final Map<Projectile, BukkitTask> tasks = new HashMap<>();
    private final Cooldown<Player> cooldown = new Cooldown<>();

    @EventHandler
    public void onPlayerUseAbility(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("ERROR_SCYTHE")) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 1, true)) return;

        if (player.isSneaking()) {
            Vector vector2 = player.getLocation().getDirection().multiply(3);
            WitherSkull wither1 = player.launchProjectile(WitherSkull.class, vector2);
            wither1.addScoreboardTag("Error_Scythe_Wither");
        }else{
            Vector vector1 = player.getLocation().getDirection().multiply(3);
            Arrow arrow1 = player.launchProjectile(Arrow.class, vector1);
            arrow1.addScoreboardTag("Error_Scythe_Arrow");
        }
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player)  {
            if (e.getEntityType() == EntityType.ARROW) {
                if (e.getEntity().getScoreboardTags().contains("Error_Scythe_Arrow")) {
                    e.getEntity().remove();
                }
        if (e.getEntity().getShooter() instanceof  Player) {
            if (e.getEntity().getShooter() instanceof Player) {
                if (e.getEntityType() == EntityType.WITHER_SKULL) {
                    if (e.getEntity().getScoreboardTags().contains("Error_Scythe_Wither")) {
                        e.getEntity().remove();
                    }
                }
            }
        }
            }
        }
    }
}


