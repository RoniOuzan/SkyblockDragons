package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

@Getter
public class FishingSkill extends AbstractSkill {
    private static final double[] statAmount = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public FishingSkill(int level, double totalXp) {
        super(
                "Fishing",
                "Visit your local pond to fish and earn Fishing XP!",
                new SkillRewards(
                        "Treasure Hunter",
                        level,
                        ChatColor.WHITE + "Increases the chance to find treasure when fishing by " + ChatColor.DARK_GRAY + (level * 0.2) + "➡" + ChatColor.GREEN + ((level + 1) * 0.2) + "%" + ChatColor.WHITE + ".",
                        StatType.HEALTH,
                        statAmount,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }


    public static FishingSkill deserialize(Map<String, Object> args) {
        return new FishingSkill((int) args.get("level"), (double) args.get("totalXp"));
    }
}
