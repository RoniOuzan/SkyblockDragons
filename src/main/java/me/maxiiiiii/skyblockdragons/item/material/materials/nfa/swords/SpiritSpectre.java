package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SpiritSpectre extends SwordMaterial {
    public SpiritSpectre() {
        super("SPIRIT_SPECTRE",
                Material.RED_ROSE,
                ItemFamily.SPIRIT_SCEPTRE,
                "Spirit Spectre",
                Rarity.LEGENDARY,
                new Stats(180, 0, 0, 0, 0, 0, 0, 0, 0, 300),
                "",
                new GuidedBat()
        );
    }

    @Override
    public int getData() {
        return 2;
    }

    public static class GuidedBat extends ItemAbility implements ItemAbilityManaCost {
        public GuidedBat() {
            super(AbilityAction.RIGHT_CLICK,
                    "Guided Bat",
                    "Shoots a guided spirit bat, following your aim and exploding for " + ChatColor.RED + "2,000 " + ChatColor.GRAY + "damage."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 250;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                Player player = e.getPlayer();

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

                            List<EntitySD> entities = Functions.loopEntities(player.getLocation(), 6);

                            for (EntitySD entity : entities) {
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
            };
        }
    }
}
