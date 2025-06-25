package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.*;
import org.bukkit.util.Vector;

import java.util.*;

public class VoidSword extends SwordMaterial {
    public VoidSword() {
        super("VOID_SWORD",
                Material.STONE_SWORD,
                ItemFamily.VOID_SWORD,
                "Void Sword",
                Rarity.LEGENDARY,
                new Stats(250, 130, 50, 10, 10, 0, 0, 0, 0, 0),
                "",
                new BlackHole()
        );
    }

    public static class BlackHole extends ItemAbility implements ItemAbilityManaCost, ItemAbilityMagicDamage {
        public BlackHole() {
            super(AbilityAction.RIGHT_CLICK,
                    "Black Hole",
                    ChatColor.GRAY + "Creates a tornado of void to that pules every monster towards the player."
            );
        }

        @Override
        public double getBaseAbilityDamage(PlayerSD player) {
            return 20_000;
        }

        @Override
        public double getBaseAbilityScaling(PlayerSD player) {
            return 0.4;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 450;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new AbilityRunnable();
        }

        private class AbilityRunnable implements PlayerAbilityRunnable {
            private static final int AMOUNT = 24;
            private Trail[] trails;

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();
                Location location = player.getLocation();

                this.trails = new Trail[AMOUNT];
                for (int i = 0; i < this.trails.length; i++) {
                    this.trails[i] = new Trail(location);
                }
                Functions.Loop(60, 1L, i -> {
                    spawnCircle(location, i);
                    spawnCircle(location, i + 0.25);
                    spawnCircle(location, i + 0.5);
                    spawnCircle(location, i + 0.75);

                    for (Trail trail : this.trails) {
                        trail.entities.addAll(Functions.loopEntities(trail.location.add(new Vector(0, 1, 0)), 1.5));
                        trail.teleport();
                    }
                });

                Functions.Wait(60L, () -> {
                    Functions.Loop(20, 1L, i -> {
                        double distance = 30.0 * (1.0 / 20.0) * 0.5;
                        for (Trail trail : trails) {
                            trail.location.add(location.clone().subtract(trail.location).toVector().normalize().multiply(distance));
                            trail.spawn();
                            trail.location.add(location.clone().subtract(trail.location).toVector().normalize().multiply(distance));
                            trail.spawn();
                            trail.teleport();
                        }
                    });

                    Functions.Wait(20L, () -> {
                        for (Trail trail : trails) {
                            for (EntitySD entity : trail.entities) {
                                entity.setVelocity(new Vector(0, 2, 0));
                            }
                        }

                        Functions.Wait(20L, () -> {
                            for (Trail trail : trails) {
                                for (EntitySD entity : trail.entities) {
                                    entity.setVelocity(new Vector(0, -3, 0));
                                }
                            }

                            Functions.Wait(6L, () -> {
                                for (Trail trail : trails) {
                                    for (EntitySD entity : trail.entities) {
                                        player.damage(new MagicEntityDamageEntity(player, entity, BlackHole.this));
                                    }
                                }
                            });
                        });
                    });
                });
            }

            private void spawnCircle(Location location, double i) {
                double radius = i * 0.5;
                double angleOffset = i * (Math.PI / 128);

                for (int j = 0; j < this.trails.length; j++) {
                    Trail trail = this.trails[j];
                    double angle = ((2 * Math.PI) / AMOUNT) * j + angleOffset;
                    Vector vector = new Vector(Math.cos(angle), 0, Math.sin(angle)).multiply(radius);

                    trail.location = location.clone().add(vector);
                    trail.spawn();
                }
            }

            private class Trail {
                private Location location;
                private final Set<EntitySD> entities;

                private Trail(Location location) {
                    this.location = location;
                    this.entities = new HashSet<>();
                }

                private void spawn() {
                    this.location.getWorld().spawnParticle(Particle.REDSTONE, this.location, 0, 0.34f, 0.15f, 0.3f, 1f);
                    this.location.getWorld().spawnParticle(Particle.REDSTONE, this.location, 0, 0.000001, 0.000001, 0.000001);
                }

                private void teleport() {
                    for (EntitySD entity : this.entities) {
                        entity.teleport(this.location);
                    }
                }
            }
        }
    }
}
