package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Spirit_Sceptre implements Listener {
    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("SPIRIT_SCEPTRE")) return;

        Player player = e.getPlayer();

        Bat bat = (Bat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
        bat.setGravity(false);
        bat.setInvulnerable(true);
        bat.setAwake(true);
        bat.addScoreboardTag("Spirit_Sceptre");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (bat.isDead()) cancel();

                Location newLocation = bat.getLocation();
                newLocation.add(newLocation.getDirection().multiply(0.5));
                if (newLocation.getBlock().getType().isSolid()) {
                    newLocation.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, bat.getLocation(), 1, 0, 0, 0, 3);

                    List<Entity> entities = Functions.loopEntities(player.getLocation(), 6);

                    for (Entity entity : entities) {
                        if (entity instanceof Creature) {
                            ((Creature) entity).damage(1, player);
                        }
                    }

                    bat.remove();
                    cancel();
                }
                bat.setVelocity(player.getLocation().getDirection().multiply(2));
            }
        }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }
}
