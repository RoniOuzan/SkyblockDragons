package me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords;

import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUtil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import org.bukkit.*;
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

    public static class DragonRage extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown, ItemAbilityMagicDamage {
        public DragonRage() {
            super(AbilityAction.RIGHT_CLICK,
                    "Dragon Rage",
                    (p, d) -> "All monsters in front of you take " + ChatColor.RED + d + " " + ChatColor.GRAY + "damage."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 100;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 2;
        }

        @Override
        public double getBaseAbilityDamage(PlayerSD player) {
            return 6000;
        }

        @Override
        public double getBaseAbilityScaling(PlayerSD player) {
            return 0.05;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Location location = player.getEyeLocation().subtract(0, 0.2, 0).add(player.getLocation().getDirection());

                ParticleUtil particle = new ParticleUtil(Particle.FLAME, 0, 0, 0, 0.01f, 1);

                Particles.circle(particle, location, 0.4, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1f, 1f);

                Functions.Wait(4L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(0.7)), 0.6, 40));

                Functions.Wait(8L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(1.4)), 0.8, 60));

                Functions.Wait(12L, () -> Particles.circle(particle, location.clone().add(location.getDirection().multiply(2.1)), 1, 80));

                List<Entity> damaged = new ArrayList<>();
                Functions.Loop(4, 4L, i -> {
                    Location newLocation = location.clone().add(location.getDirection().multiply(i));
                    location.getWorld().spawnParticle(Particle.LAVA, newLocation, 3, 0, 0, 0, 1);

                    Functions.loopEntities(newLocation, 2).stream().filter(entity -> !(entity instanceof PlayerSD)).forEach(entity -> {
                        if (!damaged.contains(entity)) {
                            player.damage(new MagicEntityDamageEntity(player, entity, this));
                            damaged.add(entity);
                        }
                    });
                });
            };
        }
    }
}
