package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

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
                        StatType.INTELLIGENCE,
                        statsAmount,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }


    public static EnchantingSkill deserialize(Map<String, Object> args) {
        return new EnchantingSkill((int) args.get("level"), (double) args.get("totalXp"));
    }
}
