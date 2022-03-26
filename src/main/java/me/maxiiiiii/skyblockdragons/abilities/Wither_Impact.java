package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.itemcreator.material.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class Wither_Impact implements Listener {
    private final Cooldown cooldown = new Cooldown();
    public static long witherShield = 0;
    public static HashMap<UUID, Integer> witherShieldHealth = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (getItemMaterial(item) instanceof NecronBladeMaterial.NecronBladeScroll) e.setCancelled(true);
        if (!(getItemMaterial(item) instanceof NecronBladeMaterial)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        ArrayList<NecronBladeMaterial.NecronBladeAbility> scrolls = getNecronScrolls(item);

        if (scrolls.size() >= 3) {
            if (cooldown(player, cooldown, 200, true)) return;

            if (players.get(player.getUniqueId()).manaCost(300, item, "Wither Impact")) return;

            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 0f);
            player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1f, 0f);

            double heal = players.get(player.getUniqueId()).getCritDamage() * 1.5;
            witherShieldHealth.put(player.getUniqueId(), (int) heal);
            witherShield = System.currentTimeMillis();

            teleportForward(player, 10);

            List<Entity> entities = loopEntities(player.getLocation(), 6);

            for (Entity entity : entities) {
                if (entity instanceof Creature) {
                    ((Creature) entity).damage(1, player);
                }
            }
            player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 3, 3, 3, 1);

            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 3, false, false));

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setHealth(player.getHealth() + (heal / 2));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 10f);
                }
            }.runTaskLater(plugin, 100L);

        } else if (scrolls.size() > 0) {
            if (cooldown(player, cooldown, 10000, true)) {
                if (scrolls.contains(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP)) {
                    if ((System.currentTimeMillis() - cooldown.getCooldown(player)) <= 5000) {
                        List<Entity> entities = loopEntities(player.getLocation(), 6);

                        for (Entity entity : entities) {
                            if (entity instanceof Creature) {
                                ((Creature) entity).damage(1, player);
                            }
                        }
                        player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 0, 0, 0, 10);
                    }
                }
                return;
            }

            if (players.get(player.getUniqueId()).getMana() >= 300) {
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 0f);
            }

            if (scrolls.contains(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP)) {

                if (players.get(player.getUniqueId()).manaCost(300, item, "Shadow Warp")) return;

                teleportForward(player, 10);

                List<Entity> entities = loopEntities(player.getLocation(), 6);

                for (Entity entity : entities) {
                    if (entity instanceof Creature) {
                        entity.setVelocity(new Vector(
                                (player.getLocation().getX() - entity.getLocation().getX()) / 3,
                                (player.getLocation().getY() - entity.getLocation().getY()) / 3,
                                (player.getLocation().getZ() - entity.getLocation().getZ()) / 3
                        ));
                    }
                }
            }

            if (scrolls.contains(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD)) {

                if (players.get(player.getUniqueId()).manaCost(150, item, "Wither Shield")) return;

                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 3, false, false));
                player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1f, 0f);
            }

            if (scrolls.contains(NecronBladeMaterial.NecronBladeAbility.IMPLOSION)) {

                if (players.get(player.getUniqueId()).manaCost(300, item, "Implosion")) return;

                player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1, 0, 0, 0, 10);
                List<Entity> entities = loopEntities(player.getLocation(), 6);

                for (Entity entity : entities) {
                    if (entity instanceof Creature) {
                        ((Creature) entity).damage(1, player);
                    }
                }
            }
        }
    }
}
