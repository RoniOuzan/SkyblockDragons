package me.maxiiiiii.skyblockdragons.player.skill.skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class FishingSkill extends Skill {
    private static final double[] statAmount = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public FishingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Fishing",
                "Visit your local pond to fish and earn Fishing XP!",
                Material.FISHING_ROD,
                new SkillRewards(
                        "Treasure Hunter",
                        l -> ChatColor.WHITE + "Increases the chance to find treasure when fishing by " + ChatColor.DARK_GRAY + (l * 0.2) + "➡" + ChatColor.GREEN + ((l + 1) * 0.2) + "%" + ChatColor.WHITE + ".",
                        StatTypes.HEALTH,
                        2,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }

    // TODO: passive
}
