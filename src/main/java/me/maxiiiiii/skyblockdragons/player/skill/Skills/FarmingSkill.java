package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

@Getter
public class FarmingSkill extends AbstractSkill {
    private static final double[] statAmount = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public FarmingSkill(int level, double totalXp) {
        super(
                "Farming",
                "Harvest crops and shear sheep to earn Farming XP!",
                new SkillRewards(
                        "Farmhand",
                        ChatColor.WHITE + "Grants " + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Farming Fortune" + ChatColor.WHITE + ", which increase your chance for multiple crops.",
                        StatType.HEALTH,
                        2,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }

    public static FarmingSkill deserialize(Map<String, Object> args) {
        return new FarmingSkill((int) args.get("level"), (double) args.get("totalXp"));
    }
}
