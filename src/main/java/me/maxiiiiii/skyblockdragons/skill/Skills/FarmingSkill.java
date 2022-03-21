package me.maxiiiiii.skyblockdragons.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

@Getter
public class FarmingSkill extends AbstractSkill {
    private static final double[] statAmount = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public FarmingSkill(int level, double totalXp) {
        super(
                "Farming",
                "Harvest crops and shear sheep to earn Farming XP!",
                new SkillRewards(
                        "Farmhand",
                        level,
                        ChatColor.WHITE + "Grants " + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Farming Fortune" + ChatColor.WHITE + ", which increase your chance for multiple crops.",
                        Stat.HEALTH,
                        statAmount,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }
}
