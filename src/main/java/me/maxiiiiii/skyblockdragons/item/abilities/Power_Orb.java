package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Power_Orb implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).contains("_POWER_ORB")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        Location location = player.getLocation().clone().add(player.getLocation().getDirection().setY(0)).subtract(0, 1, 0);

        new PowerOrb(PowerOrb.Type.valueOf(Functions.getId(item).split("_POWER")[0]), location);
    }
}