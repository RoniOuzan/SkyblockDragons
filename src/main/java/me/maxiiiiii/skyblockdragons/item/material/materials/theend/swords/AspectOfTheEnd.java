package me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AspectOfTheEnd extends SwordMaterial {
    public AspectOfTheEnd() {
        super("ASPECT_OF_THE_END",
                Material.DIAMOND_SWORD,
                ItemFamily.ASPECT_OF_THE_END,
                "Aspect of the End",
                Rarity.RARE,
                new Stats(100, 100, 0, 0, 0, 0, 0, 0, 0, 0),
                "",
                new Transmission(8),
                new SkillRequirement(SkillType.COMBAT, 12)
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Transmission extends ItemAbility {
        private final double blocks;

        public Transmission(double blocks) {
            super(AbilityAction.RIGHT_CLICK,
                    "Transmission",
                    ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "8 blocks " + ChatColor.GRAY + "ahead of you and gain " + ChatColor.GREEN + "+50 " + StatType.SPEED.getIconAndText() + " NEW_LINE for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + ".",
                    50,
                    0
            );
            this.blocks = blocks;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                Player player = e.getPlayer();

                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
                Functions.teleportForward(player, blocks);
            };
        }
    }
}
