package me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;

import java.util.function.Function;

@Getter
public abstract class WisdomStat extends StatType {
    private final SkillType skill;

    public WisdomStat(String name, String icon, ChatColor color, String description, Function<PlayerSD, Double> maxLevel, SkillType skill) {
        super(name, icon, color, description, maxLevel);
        this.skill = skill;
    }

    public WisdomStat(String name, String icon, ChatColor color, String description, SkillType skill) {
        super(name, icon, color, description);
        this.skill = skill;
    }
}
