package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
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
