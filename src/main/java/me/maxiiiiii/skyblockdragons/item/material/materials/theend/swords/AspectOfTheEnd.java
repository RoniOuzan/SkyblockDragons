package me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

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

    public static class Transmission extends ItemAbility implements ItemAbilityManaCost {
        private final double blocks;

        public Transmission(double blocks) {
            super(AbilityAction.RIGHT_CLICK,
                    "Transmission",
                    ChatColor.GRAY + "Teleport " + ChatColor.GREEN + blocks + " blocks " + ChatColor.GRAY + "ahead of you and gain " + ChatColor.GREEN + "+50 " + StatTypes.SPEED + " NEW_LINE for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + "."
            );
            this.blocks = blocks;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 50;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
                Functions.teleportForward(player, blocks);
            };
        }
    }
}
