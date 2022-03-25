package me.maxiiiiii.skyblockdragons.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

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
                        Stat.STRENGTH,
                        statsAmount,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }
}
