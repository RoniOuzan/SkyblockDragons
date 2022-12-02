package me.maxiiiiii.skyblockdragons.item.stats.interfaces;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;

import java.util.function.Function;

@Getter
public abstract class WisdomStat extends StatType {
    private final SkillType skill;

    public WisdomStat(String name, String icon, ChatColor color, Function<PlayerSD, Double> maxLevel, Function<PlayerSD, Double> base, SkillType skill) {
        super(name, icon, color, skill.toString() + " Wisdom increases how much " + skill + " Skill XP that you gain.", maxLevel, base);
        this.skill = skill;
    }

    public WisdomStat(String name, String icon, ChatColor color, double base, SkillType skill) {
        super(name, icon, color, skill.toString() + " Wisdom increases how much " + skill + " Skill XP that you gain.", base);
        this.skill = skill;
    }

    public WisdomStat(String name, String icon, ChatColor color, SkillType skill) {
        super(name, icon, color, skill.toString() + " Wisdom increases how much " + skill + " Skill XP that you gain.");
        this.skill = skill;
    }
}
