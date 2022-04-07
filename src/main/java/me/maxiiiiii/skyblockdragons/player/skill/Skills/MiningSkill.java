package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

@Getter
public class MiningSkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public MiningSkill(int level, double totalXp) {
        super(
                "Mining",
                "Spelunk island for ores and valuable materials to earn Mining XP!",
                new SkillRewards(
                        "Spelunker",
                        level,
                        ChatColor.WHITE + "Graints " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Mining Fortune" + ChatColor.WHITE + ", which increases your chance for multiple ore drops.",
                        StatType.DEFENSE,
                        statsAmount,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }


    public static MiningSkill deserialize(Map<String, Object> args) {
        return new MiningSkill((int) args.get("level"), (double) args.get("totalXp"));
    }
}
