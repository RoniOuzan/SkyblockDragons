package me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class ErrorScythe extends SwordMaterial {
    public ErrorScythe() {
        super("ERROR_SCYTHE",
                Material.DIAMOND_HOE,
                ItemFamily.ERROR_SCYTHE,
                "Error Scythe",
                Rarity.LEGENDARY,
                new Stats(1500, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "",
                new WitherSkullAbility(),
                new ArrowAbility(),
                new SkillRequirement(SkillType.COMBAT, 0)
        );
    }

    public static class ArrowAbility extends ItemAbility implements Listener {
        protected ArrowAbility() {
            super(AbilityAction.RIGHT_CLICK,
                    "Arrow",
                    ChatColor.GRAY + "Shoots " + ChatColor.GREEN + "arrows"
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();
                Vector vector = player.getLocation().getDirection().multiply(3);
                Arrow arrow = player.launchProjectile(Arrow.class, vector);
                arrow.addScoreboardTag("Error_Scythe_Arrow");
            };
        }

        @EventHandler
        public void onProjectileHit(ProjectileHitEvent e) {
            if (e.getEntityType() == EntityType.ARROW) {
                if (e.getEntity().getScoreboardTags().contains("Error_Scythe_Arrow")) {
                    e.getEntity().remove();
                }
            }
        }
    }

    public static class WitherSkullAbility extends ItemAbility implements Listener {
        protected WitherSkullAbility() {
            super(AbilityAction.RIGHT_SHIFT_CLICK,
                    "Wither Skull",
                    ChatColor.GRAY + "Shoots " + ChatColor.GOLD + "Wither Skull"
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();
                Vector vector = player.getLocation().getDirection().multiply(3);
                WitherSkull wither = player.launchProjectile(WitherSkull.class, vector);
                wither.addScoreboardTag("Error_Scythe_Wither");
            };
        }

        @EventHandler
        public void onProjectileHit(ProjectileHitEvent e) {
            if (e.getEntityType() == EntityType.WITHER_SKULL) {
                if (e.getEntity().getScoreboardTags().contains("Error_Scythe_Wither")) {
                    e.getEntity().remove();
                }
            }
        }
    }
}
