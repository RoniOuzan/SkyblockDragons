package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.Functions.*;

public class Midas_Staff implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("MIDAS_STAFF")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 1000, true)) return;

        Vector direction = player.getLocation().getDirection().clone();
        direction.setY(0);
        direction.normalize();
        Location location = player.getLocation().add(direction.clone().multiply(1));
        location.add(0, 1, 0);

        Loop(7, 3L, (i) -> {
            Location location1 = location.clone().add(direction.clone().multiply(i));
            Location location2 = location.clone().add(direction.clone().multiply(i));
            location2.setYaw(location2.getYaw() + 90);
            location2.add(location2.clone().getDirection().multiply(1));

            Location location3 = location.clone().add(direction.clone().multiply(i));
            location3.setYaw(location3.getYaw() - 90);
            location3.add(location3.clone().getDirection().multiply(1));

            spawnBlock(location1);
            spawnBlock(location2);
            spawnBlock(location3);
        });
    }

    @EventHandler
    public void onFallingBlockLand(EntityChangeBlockEvent e) {
        if (e.getEntity().getName().contains("MidasStaff")) {
            e.setCancelled(true);
        }
    }

    private static void spawnBlock(Location location) {
        FallingBlock block = location.getWorld().spawnFallingBlock(location, new MaterialData(Material.GOLD_BLOCK));
        block.setInvulnerable(true);
        block.setDropItem(false);
        block.setCustomName("MidasStaff");
        block.setHurtEntities(false);
    }
}
