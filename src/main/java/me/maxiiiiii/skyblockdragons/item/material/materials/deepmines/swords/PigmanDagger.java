package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.swords;

import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import org.bukkit.*;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class PigmanDagger extends SwordMaterial {
    public PigmanDagger() {
        super("PIGMAN_DAGGER",
                Material.GOLD_SWORD,
                ItemFamily.PIGMAN,
                "Pigman Dagger",
                Rarity.UNCOMMON,
                new Stats(65, 25, 10, 0, 0, 0, 0, 0, 0, 20),
                "",
                new Burning()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Burning extends ItemAbility implements ItemAbilityManaCost, ItemAbilityMagicDamage, ItemAbilityCooldown {
        public Burning() {
            super(AbilityAction.RIGHT_CLICK,
                    "Burning",
                    "Cast vortex of " + ChatColor.RED + "flames " + ChatColor.GRAY + "towards enemies that deals " + ChatColor.RED + "ABILITY_DAMAGE " + ChatColor.GRAY + "damage."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 15;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 5;
        }

        @Override
        public double getBaseAbilityDamage(PlayerSD player) {
            return 40;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();
                Location location = player.getEyeLocation().subtract(0, 0.2, 0).add(player.getLocation().getDirection());

                ParticleUil particle = new ParticleUil(Particle.FLAME, 0, 0, 0, 0.01f, 1);

                Particles.circle(particle, location, 0.4, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_PIG_HURT, 1f, 0.5f);

                Functions.Wait(4L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(0.7)), 0.6, 40));
                Functions.Wait(8L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(1.4)), 0.8, 60));
                Functions.Wait(12L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(2.1)), 1, 80));

                List<Entity> damaged = new ArrayList<>();
                Functions.Loop(4, 4L, i -> {
                    Location newLocation = location.clone().add(location.getDirection().multiply(i));
                    location.getWorld().spawnParticle(Particle.LAVA, newLocation, 3, 0, 0, 0, 1);

                    for (Entity entity : Functions.loopEntities(newLocation, 2)) {
                        if (entity instanceof Creature && !damaged.contains(entity)) {
                            player.makeDamage(entity, Damage.DamageType.MAGIC, 1, this.getFinalAbilityDamage(player), this.getFinalAbilityScaling(player));
                            damaged.add(entity);
                        }
                    }
                });
            };
        }
    }
}
