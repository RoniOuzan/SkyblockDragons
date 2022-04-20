package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;
import static me.maxiiiiii.skyblockdragons.util.Functions.getTargetEntity;
import static me.maxiiiiii.skyblockdragons.util.Functions.randomDouble;

public class Troll_Eye implements Listener {
    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("TROLL_EYE")) return;

        PlayerSD player = e.getPlayer();

        Entity targetEntity;
        try {
            targetEntity = getTargetEntity(player, 100);
        } catch (NullPointerException ex) {
            return;
        }

        new BukkitRunnable() {
            double times = 0;
            @Override
            public void run() {
                if (times >= 20 || targetEntity.isDead()) return;
                ((LivingEntity) targetEntity).launchProjectile(EnderPearl.class, new Vector(randomDouble(-300, 300) / 100, randomDouble(-300, 300) / 100, randomDouble(-300, 300) / 100));
                times++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}
