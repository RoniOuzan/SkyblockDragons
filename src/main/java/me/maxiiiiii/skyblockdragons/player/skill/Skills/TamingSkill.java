package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class TamingSkill extends AbstractSkill {
    public TamingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Taming",
                "Level up pets to earn Pet XP!",
                Material.MONSTER_EGG,
                new SkillRewards(
                        "Zoologist",
                        l -> ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + l + "➡" + ChatColor.GREEN + (l + 1) + "% " + ChatColor.WHITE + "extra pet exp.",
                        StatType.SPEED,
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
