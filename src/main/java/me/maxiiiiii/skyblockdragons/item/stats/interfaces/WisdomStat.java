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

    public WisdomStat(String name, String icon, ChatColor color, String description, Function<PlayerSD, Double> maxLevel, Function<PlayerSD, Double> base, SkillType skill) {
        super(name, icon, color, description, maxLevel, base);
        this.skill = skill;
    }

    public WisdomStat(String name, String icon, ChatColor color, String description, double base, SkillType skill) {
        super(name, icon, color, description, base);
        this.skill = skill;
    }

    public WisdomStat(String name, String icon, ChatColor color, String description, SkillType skill) {
        super(name, icon, color, description);
        this.skill = skill;
    }
}
