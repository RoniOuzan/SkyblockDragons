package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

public class TamingSkill extends AbstractSkill {
    public TamingSkill(int level, double totalXp) {
        super(
                "Taming",
                "Level up pets to earn Pet XP!",
                new SkillRewards(
                        "Zoologist",
                        level,
                        ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + level + "➡" + ChatColor.GREEN + (level + 1) + "% " + ChatColor.WHITE + "extra pet exp.",
                        StatType.SPEED,
                        1d,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }


    public static TamingSkill deserialize(Map<String, Object> args) {
        return new TamingSkill((int) args.get("level"), (double) args.get("totalXp"));
    }
}
