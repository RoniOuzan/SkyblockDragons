package me.maxiiiiii.skyblockdragons.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

@Getter
public class MiningSkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public MiningSkill(int level, double totalXp) {
        super(
                "Mining",
                "Spelunk island for ores and valuable materials to earn Mining XP!",
                new SkillRewards(
                        "Spelunker",
                        level,
                        ChatColor.WHITE + "Graints " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Mining Fortune" + ChatColor.WHITE + ", which increases your chance for multiple ore drops.",
                        Stat.DEFENSE,
                        statsAmount,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }
}
