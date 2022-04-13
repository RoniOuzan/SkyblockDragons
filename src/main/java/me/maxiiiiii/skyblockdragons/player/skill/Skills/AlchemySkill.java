package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;

import java.util.Map;

@Getter
public class AlchemySkill extends AbstractSkill {
    public AlchemySkill(int level, double totalXp) {
        super("Alchemy",
                "Brew potions to earn Alchemy XP!",
                new SkillRewards(
                        "Brewer",
                        ChatColor.GRAY + "Potion that you brew have a " + ChatColor.DARK_GRAY + level + "➡" + ChatColor.GREEN + (level + 1) + "% " + ChatColor.WHITE + "longer duration.",
                        StatType.INTELLIGENCE,
                        2,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }
}
