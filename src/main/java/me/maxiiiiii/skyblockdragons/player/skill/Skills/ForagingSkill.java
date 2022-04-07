package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

@Getter
public class ForagingSkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public ForagingSkill(int level, double totalXp) {
        super(
                "Foraging",
                "Cut trees and forage for other plants to earn Foraging XP!",
                new SkillRewards(
                        "Logger,",
                        level,
                        ChatColor.WHITE + "Grants " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Foraging Fortune " + ChatColor.WHITE + ", which increase your chance for multiple logs.",
                        StatType.STRENGTH,
                        statsAmount,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }


    public static ForagingSkill deserialize(Map<String, Object> args) {
        return new ForagingSkill((int) args.get("level"), (double) args.get("totalXp"));
    }
}
