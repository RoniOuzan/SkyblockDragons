package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
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

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Midas_Staff implements Listener {
    private final Cooldown<Player> cooldown = new Cooldown<>();

    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("MIDAS_STAFF")) return;

        PlayerSD player = e.getPlayer();

        Vector direction = player.getLocation().getDirection().clone();
        direction.setY(0);
        direction.multiply(5);
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
