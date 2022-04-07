package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
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

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Terminator implements Listener {
    private final Map<Projectile, BukkitTask> tasks = new HashMap<>();
    private final Cooldown<Player> cooldown = new Cooldown<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("TERMINATOR")) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 200, false)) return;

//        Vector v = new Vector(player.getLocation().getDirection().getX() * 2, player.getLocation().getDirection().getY() * 2, player.getLocation().getDirection().getZ() * 2);
//        Arrow a1 = player.launchProjectile(Arrow.class, v);
//        Arrow a2 = player.launchProjectile(Arrow.class, v.clone().add(new Vector(0.13, 0, 0.13)));
//        Arrow a3 = player.launchProjectile(Arrow.class, v.clone().add(new Vector(-0.13, 0, -0.13)));
//        a2.getLocation().setYaw(a2.getLocation().getYaw() + 5);
//        a3.getLocation().setYaw(a2.getLocation().getYaw() - 5);
//        a1.addScoreboardTag("TERMINATOR");
//        a2.addScoreboardTag("TERMINATOR");
//        a3.addScoreboardTag("TERMINATOR");

        Vector vector1 = player.getLocation().getDirection().multiply(3);
        Vector vector2 = getVector(player, 5, 0, 3);
        Vector vector3 = getVector(player, -5, 0, 3);
        Arrow arrow1 = player.launchProjectile(Arrow.class, vector1);
        Arrow arrow2 = player.launchProjectile(Arrow.class, vector2);
        Arrow arrow3 = player.launchProjectile(Arrow.class, vector3);
        arrow1.addScoreboardTag("Terminator");
        arrow2.addScoreboardTag("Terminator");
        arrow3.addScoreboardTag("Terminator");
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType() == EntityType.ARROW) {
                Player player = (Player) e.getEntity().getShooter();
                if (Functions.getId(player.getEquipment().getItemInMainHand()).equals("Terminator")) {
                    tasks.put(e.getEntity(), Bukkit.getScheduler().runTaskTimer(SkyblockDragons.plugin, () -> {
                        e.getEntity().getWorld().spawnParticle(Particle.SPELL_WITCH, e.getEntity().getLocation(), 1, 0, 0, 0, 0);
                        e.getEntity().getWorld().spawnParticle(Particle.SMOKE_NORMAL, e.getEntity().getLocation(), 1, 0, 0, 0, 0);
                    }, 0L, 1L));
                }
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType() == EntityType.ARROW) {
                if (e.getEntity().getScoreboardTags().contains("Terminator")) {
                    e.getEntity().remove();
                }
                BukkitTask task = tasks.get(e.getEntity());
                if (task != null) {
                    task.cancel();
                    tasks.remove(e.getEntity());
                }
            }
        }
    }
}
