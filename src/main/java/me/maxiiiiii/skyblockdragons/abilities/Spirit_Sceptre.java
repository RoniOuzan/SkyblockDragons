package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Spirit_Sceptre implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("SPIRIT_SCEPTRE")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        Location location = player.getLocation();

        if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
        if (Functions.cooldown(player, cooldown, 50, false)) return;

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
