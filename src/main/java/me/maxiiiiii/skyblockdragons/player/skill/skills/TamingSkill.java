package me.maxiiiiii.skyblockdragons.player.skill.skills;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class TamingSkill extends Skill {
    public TamingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Taming",
                "Level up pets to earn Pet XP!",
                Material.MONSTER_EGG,
                new SkillRewards(
                        "Zoologist",
                        l -> ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + l + "➡" + ChatColor.GREEN + (l + 1) + "% " + ChatColor.WHITE + "extra pet exp.",
                        StatTypes.SPEED,
                        1,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }

    // TODO: passive
}
