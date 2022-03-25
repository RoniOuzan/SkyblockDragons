package me.maxiiiiii.skyblockdragons.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

@Getter
public class EnchantingSkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public EnchantingSkill(int level, double totalXp) {
        super(
                "Enchanting",
                "Enchant items to earn Enchanting XP!",
                new SkillRewards(
                        "Conjurer",
                        level,
                        ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.WHITE + "more experience orbs from any source.",
                        Stat.INTELLIGENCE,
                        statsAmount,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }
}
