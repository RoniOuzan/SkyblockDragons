package me.maxiiiiii.skyblockdragons.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import me.maxiiiiii.skyblockdragons.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.skill.SkillRewards;
import org.bukkit.ChatColor;

@Getter
public class CombatSkill extends AbstractSkill {
    public CombatSkill(int level, double totalXp) {
        super("Combat",
                "Fight mobs and players to earn Combat XP!",
                new SkillRewards(
                    "Warrior",
                        level,
                        ChatColor.WHITE + "Deal " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + level + "➡" + ChatColor.GREEN + (level + 1) + "% " + ChatColor.WHITE + "more damage to mobs",
                        Stat.CRIT_CHANCE,
                        0.5,
                        coinsAmount
                ),
                level,
                60,
                totalXp);
    }
}
