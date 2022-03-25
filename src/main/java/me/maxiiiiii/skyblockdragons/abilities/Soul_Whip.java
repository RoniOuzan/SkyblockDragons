package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Soul_Whip implements Listener {
    private final Cooldown soulWhipCD = new Cooldown();
    private final int AMOUNT = 15;

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

        if (!Functions.getId(item).equals("SOUL_WHIP")) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("SOUL_WHIP")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;


        Player player = e.getPlayer();
        Location location = player.getEyeLocation().add(0, -0.5, 0);

        if (Functions.cooldown(player, soulWhipCD, 500, false)) return;

        new BukkitRunnable() {
            double i = 0;
            @Override
            public void run() {
                if (i >= 180) cancel();
                for (double j = i; j < i + AMOUNT; j++) {
                    double rad = Math.toRadians(j);
                    Location newLocation = location.clone().add(location.getDirection().multiply(j / 15));
                    newLocation.add(
                            0,
                            Math.sin(rad) * 1.5,
                            0
                    );

                    location.getWorld().spawnParticle(Particle.REDSTONE, newLocation, 0, 0.3, 0, 0);
                    location.getWorld().spawnParticle(Particle.REDSTONE, newLocation, 0, 0.000001, 0.000001, 0.000001);
                    for (Entity entity : newLocation.getWorld().getNearbyEntities(newLocation, 1.5, 1.5, 1.5)) {
                        if (entity instanceof Creature) {
                            ((Creature) entity).damage(1, player);
                        }
                    }
                }
                i += AMOUNT;
            }
        }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
    }
}
