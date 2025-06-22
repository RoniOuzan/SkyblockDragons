package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
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

    public static class GuidedBat extends ItemAbility implements ItemAbilityManaCost, ItemAbilityMagicDamage {
        public GuidedBat() {
            super(AbilityAction.RIGHT_CLICK,
                    "Guided Bat",
                    "Shoots a guided spirit bat, following your aim and exploding for " + ChatColor.RED + "2,000 " + ChatColor.GRAY + "damage."
            );
        }

        @Override
        public double getBaseAbilityDamage(PlayerSD player) {
            return 15_000;
        }

        @Override
        public double getBaseAbilityScaling(PlayerSD player) {
            return 0.25;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 250;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Bat bat = (Bat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
                bat.setGravity(false);
                bat.setInvulnerable(true);
                bat.setAwake(true);
                bat.addScoreboardTag("Spirit_Sceptre");

                Functions.While(() -> !bat.isDead(), 1L, i -> {

                });
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (bat.isDead()) cancel();

                        Location newLocation = bat.getLocation();
                        newLocation.add(player.getLocation().getDirection().multiply(0.5));
                        if (newLocation.getBlock().getType().isSolid()) {
                            newLocation.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, bat.getLocation(), 1, 0, 0, 0, 5);

                            for (EntitySD entity : Functions.loopEntities(newLocation, 6)) {
                                player.damage(new MagicEntityDamageEntity(player, entity, GuidedBat.this));
                            }

                            bat.remove();
                            cancel();
                        } else {
                            bat.setVelocity(player.getLocation().getDirection().multiply(2));
                        }
                    }
                }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
            };
        }
    }
}
