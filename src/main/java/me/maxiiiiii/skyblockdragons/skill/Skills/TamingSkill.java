package me.maxiiiiii.skyblockdragons.skill.Skills;

import me.maxiiiiii.skyblockdragons.itemcreator.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

public class TamingSkill extends AbstractSkill {
    public TamingSkill(int level, double totalXp) {
        super(
                "Taming",
                "Level up pets to earn Pet XP!",
                new SkillRewards(
                        "Zoologist",
                        level,
                        ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + level + "➡" + ChatColor.GREEN + (level + 1) + "% " + ChatColor.WHITE + "extra pet exp.",
                        Stat.SPEED,
                        1d,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }
}
