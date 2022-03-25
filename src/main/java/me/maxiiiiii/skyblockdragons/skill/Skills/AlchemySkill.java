package me.maxiiiiii.skyblockdragons.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

@Getter
public class AlchemySkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public AlchemySkill(int level, double totalXp) {
        super("Alchemy",
                "Brew potions to earn Alchemy XP!",
                new SkillRewards(
                        "Brewer",
                        level,
                        ChatColor.GRAY + "Potion that you brew have a " + ChatColor.DARK_GRAY + level + "➡" + ChatColor.GREEN + (level + 1) + "% " + ChatColor.WHITE + "longer duration.",
                        Stat.INTELLIGENCE,
                        statsAmount,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }
}
