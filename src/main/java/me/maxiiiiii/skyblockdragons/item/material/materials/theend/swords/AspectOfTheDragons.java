package me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords;

import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import org.bukkit.*;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class AspectOfTheDragons extends SwordMaterial {
    public AspectOfTheDragons() {
        super("ASPECT_OF_THE_DRAGONS",
                Material.DIAMOND_SWORD,
                ItemFamily.DRAGON,
                "Aspect of the Dragons",
                Rarity.LEGENDARY,
                new Stats(225, 100, 0, 0, 0, 0),
                "",
                new DragonRage(),
                new SkillRequirement(SkillType.COMBAT, 18)
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class DragonRage extends ItemAbility {
        public DragonRage() {
            super(AbilityAction.RIGHT_CLICK,
                    "Dragon Rage",
                    "All monsters in front of you take " + ChatColor.RED + "ABILITY_DAMAGE " + ChatColor.GRAY + "damage.",
                    100,
                    false,
                    2,
                    6000,
                    0.05
            );
        }

        @Override
        public Runnable onAbilityUse(PlayerUseAbilityEvent e) {
            return () -> {
                PlayerSD player = e.getPlayer();

                Location location = player.getEyeLocation().subtract(0, 0.2, 0).add(player.getLocation().getDirection());

                ParticleUil particle = new ParticleUil(Particle.FLAME, 0, 0, 0, 0.01f, 1);

                Particles.circle(particle, location, 0.4, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1f, 1f);

                Functions.Wait(4L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(0.7)), 0.6, 40));

                Functions.Wait(8L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(1.4)), 0.8, 60));

                Functions.Wait(12L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(2.1)), 1, 80));

                List<Entity> damaged = new ArrayList<>();
                Functions.Loop(4, 4L, i -> {
                    Location newLocation = location.clone().add(location.getDirection().multiply(i));
                    location.getWorld().spawnParticle(Particle.LAVA, newLocation, 3, 0, 0, 0, 1);

                    for (Entity entity : Functions.loopEntities(newLocation, 2)) {
                        if (entity instanceof Creature && !damaged.contains(entity)) {
                            player.makeDamage(entity, Damage.DamageType.MAGIC, 1, this.getAbilityDamage(), this.getAbilityScaling());
                            damaged.add(entity);
                        }
                    }
                });
            };
        }
    }
}
