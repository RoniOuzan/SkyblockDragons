package me.maxiiiiii.skyblockdragons.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.Functions.getId;
import static me.maxiiiiii.skyblockdragons.Functions.getTargetEntity;
import static me.maxiiiiii.skyblockdragons.Functions.randomDouble;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class Troll_Eye implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("TROLL_EYE")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        e.setCancelled(true);

        Player player = e.getPlayer();

        Entity targetEntity;
        try {
            targetEntity = getTargetEntity(player, 100);
        } catch (NullPointerException ex) {return;}

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            double times = 0;
            @Override
            public void run() {
                if (times >= 20) return;
                ((LivingEntity) targetEntity).launchProjectile(EnderPearl.class, new Vector(randomDouble(-300, 300) / 100, randomDouble(-300, 300) / 100, randomDouble(-300, 300) / 100));
                times++;
            }
        }, 0L, 1L);
    }
}
