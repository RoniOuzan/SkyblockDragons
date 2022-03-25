package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Mana_Flux_Power_Orb implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("MANA_FLUX_POWER_ORB")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        Location location = player.getLocation();

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        ItemStack head = new Item(Functions.getItemMaterial(item));
        stand.setHelmet(head);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.setVisible(false);
        stand.setMarker(true);
        stand.addScoreboardTag("PowerOrb");

        new BukkitRunnable() {
            double i = 0;
            @Override
            public void run() {
                if (stand.isDead() || i >= 300) {
                    stand.remove();
                    cancel();
                }

                i++;
                Location newLocation = stand.getLocation();
                if (i % 20 < 10) {
                    newLocation.add(0, 0.1, 0);
                } else {
                    newLocation.add(0, -0.1, 0);
                }
                newLocation.setYaw(newLocation.getYaw() + 10f);

                stand.teleport(newLocation);

                Location particleLocation = stand.getLocation().add(0, 1.7, 0);
                particleLocation.add(stand.getLocation().getDirection().multiply(0.4));
                stand.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 0, 0.15, 0.15, 0.9);
            }
        }.runTaskTimer(SkyblockDragons.plugin, 0L, 2L);
    }
}
