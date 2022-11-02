package me.maxiiiiii.skyblockdragons.item.material.materials.other.bows;

import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.ExplosionEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.events.events.ProjectileHitEvent;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExplosiveBow extends BowMaterial {
    public ExplosiveBow() {
        super("EXPLOSIVE_BOW",
                Material.BOW,
                ItemFamily.NULL,
                "Explosive Bow",
                Rarity.EPIC,
                new Stats(100, 30, 0, 0, 0, 0),
                "",
                new Explosive(5),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    public static class Explosive extends ItemAbility implements Listener {
        private final double radius;

        protected Explosive(double radius) {
            super(AbilityAction.NONE,
                    "Explosive",
                    "Arrows explode on impact"
            );
            this.radius = radius;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {};
        }

        @EventHandler
        public void onProjectileHit(ProjectileHitEvent e) {
            if (e.getMaterial() == Items.get("EXPLOSIVE_BOW")) {
                Projectile projectile = e.getEntity();
                PlayerSD player = e.getShooter();
                player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, projectile.getLocation(), 1, radius, radius, radius);
                for (EntitySD entity : Functions.loopEntities(projectile.getLocation(), radius)) {
                    player.damage(new ExplosionEntityDamageEntity(player, entity));
                }
            }
        }
    }
}
