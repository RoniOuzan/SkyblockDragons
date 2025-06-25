package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUtil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MagicSpeller extends ToolMaterial {
    public MagicSpeller() {
        super("MAGIC_SPELLER",
                Material.EYE_OF_ENDER,
                ItemFamily.NULL,
                "Magic Speller",
                ItemType.WAND,
                Rarity.LEGENDARY,
                "",
                new Spell()
        );
    }

    private static class Spell extends ItemAbility implements ItemAbilityManaCost, ItemAbilityMagicDamage, Listener {

        protected Spell() {
            super(AbilityAction.RIGHT_CLICK,
                    "Spell",
                    "Spell..."
            );
        }

        @Override
        public double getBaseAbilityDamage(PlayerSD player) {
            return 5000;
        }

        @Override
        public double getBaseAbilityScaling(PlayerSD player) {
            return 0.5;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 250;
        }

        @Override
        protected PlayerAbilityRunnable setupAbility() {
            return new SpellRunnable();
        }

        private class SpellRunnable implements PlayerAbilityRunnable {
            private static final int TRAIL_LENGTH = 36;
            private static final int AMOUNT_OF_TRAILS = 4;
            private static final double TRAIL_DX = (2 * Math.PI) / AMOUNT_OF_TRAILS;
            private static final double ROTATIONS_PER_TRAIL = 1.5;
            private static final double ANGLE_DX = ROTATIONS_PER_TRAIL * (2 * Math.PI) / TRAIL_LENGTH;
            private static final double RADIUS = 1;
            private static final double VELOCITY_Y = 2 * (1.0 / 20.0);
            private static final double VELOCITY_CENTER = 5 * (1.0 / 20.0);
            private final Vector OFFSET = new Vector(0, 0.8, 0);
            private final ParticleUtil particle = new ParticleUtil(Particle.SPELL_WITCH , 0, 0, 0, 0, 5);
            private final ParticleUtil coreParticle = new ParticleUtil(Particle.REDSTONE, 0.34, 0.15, 0.3, 1, 5);

            private boolean isLevitating = false;
            private List<EntitySD> entities = new ArrayList<>();
            private Location location;

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();

                if (!this.entities.isEmpty()) {
                    return;
                }

                this.isLevitating = true;
                this.location = player.getEyeLocation();
                this.entities = Functions.loopEntities(this.location, 30);
                for (EntitySD entity : entities) {
                    Functions.Loop(10, 2L, i -> {
                        particle.spawn(entity.getEyeLocation().add(OFFSET));
                    }, i -> {
                        double totalHeight = entity.getEyeHeight() + (OFFSET.getY() * 2);
                        double hDX = totalHeight / TRAIL_LENGTH;
                        Functions.Loop(TRAIL_LENGTH, 1L, j -> {
                            double a = ANGLE_DX * j;
                            double h = hDX * j;
                            double rPercent = Math.abs(((double) j / (TRAIL_LENGTH / 2.0)) - 1);
                            double r = (1 - Math.pow(rPercent, 4)) * RADIUS;
                            Location center = entity.getEyeLocation().add(OFFSET);
                            for (int k = 0; k < AMOUNT_OF_TRAILS; k++) {
                                a += TRAIL_DX;
                                Location newLocation = center.clone().add(new Vector(Math.cos(a), 0, Math.sin(a)).multiply(r).setY(-h));
                                particle.spawn(newLocation);
                            }

                            entity.setVelocity(new Vector(0, VELOCITY_Y, 0));
                        }, j -> Functions.While(() -> this.isLevitating, 1L, k -> entity.setVelocity(new Vector())));
                    });
                }

                Functions.While(() -> !this.entities.isEmpty(), 2L, i -> {
                    Particles.sphere(particle, this.location, 0.7, 6);
                    Particles.sphere(coreParticle, this.location, 0.5, 6);
                });
            }

            private void onLeftClick(PlayerSD player) {
                this.isLevitating = false;

                Bukkit.getScheduler().runTaskAsynchronously(SkyblockDragons.plugin, () -> {
                    long startedAt = System.currentTimeMillis();
                    Functions.While(() -> !this.entities.isEmpty() && System.currentTimeMillis() - startedAt <= 15_000, 2L, i -> {
                        List<EntitySD> toRemove = new ArrayList<>();
                        for (EntitySD entity : this.entities) {
                            if (entity.getLocation().distance(this.location) <= 1) {
                                toRemove.add(entity);
                                player.damage(new MagicEntityDamageEntity(player, entity, Spell.this));
                            }

                            entity.setVelocity(this.location.clone().subtract(entity.getLocation()).toVector().normalize().multiply(VELOCITY_CENTER));
                            particle.spawn(entity.getLocation());
                        }
                        this.entities.removeAll(toRemove);
                    }, i -> this.entities.clear());
                });
            }
        }

        @EventHandler
        public void onClick(PlayerInteractEvent e) {
            if (!Functions.getId(e.getItem()).equals("MAGIC_SPELLER")) return;
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) return;

            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            SpellRunnable spell = (SpellRunnable) getAbilityOfPlayer(player).getRunnable();

            spell.onLeftClick(player);
        }
    }
}
