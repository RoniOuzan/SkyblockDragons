package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class ShadowFury extends SwordMaterial {
    public ShadowFury() {
        super("SHADOW_FURY",
                Material.DIAMOND_SWORD,
                ItemFamily.SHADOW_FURY,
                "Shadow Fury",
                Rarity.LEGENDARY,
                new Stats(300, 125, 0, 0, 0, 0, 0, 0, 30, 0),
                "",
                new ShadowFuryAbility()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class ShadowFuryAbility extends ItemAbility implements ItemAbilityCooldown {
        public ShadowFuryAbility() {
            super(AbilityAction.RIGHT_CLICK,
                    "Shadow Fury",
                    "Rapidly teleports you to up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "enemies within " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "blocks, rooting each of them and allowing you to hit them."
            );
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 15;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                List<Entity> nearbyEntities = player.getNearbyEntities(10, 10, 10);
                ArrayList<Entity> entities = new ArrayList<>();
                for (Entity entity : nearbyEntities) {
                    if (entity instanceof Creature) {
                        entities.add(entity);
                    }
                }
                if (entities.size() == 0) return;

                new BukkitRunnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        if (i >= 5) return;
                        Entity nearestEntity = entities.get(0);
                        for (Entity entity : entities) {
                            if (player.getLocation().distance(entity.getLocation()) < player.getLocation().distance(nearestEntity.getLocation())) {
                                nearestEntity = entity;
                            }
                        }
                        Location l = nearestEntity.getLocation().add(nearestEntity.getLocation().getDirection().multiply(-1));
                        l.setY(nearestEntity.getLocation().getY() + 0.2);
                        player.teleport(l);
                        entities.remove(nearestEntity);
                        if (entities.size() == 0) return;
                        i++;
                    }
                }.runTaskTimer(plugin, 0L, 10L);
            };
        }
    }
}
