package me.maxiiiiii.skyblockdragons.util.objects.requirements;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

public class SkillRequirement extends Requirement {
    public static final SkillRequirement NULL = new SkillRequirement(SkillType.COMBAT, 0);

    private final SkillType skillType;

    public SkillRequirement(SkillType skillType, int level) {
        super(RequirementType.SKILL, level);
        this.skillType = skillType;
    }

    @Override
    public boolean hasRequirement(PlayerSD player) {
        if (player == null) return false;

        return player.getSkills().get(skillType).getLevel() >= this.level;
    }

    @Override
    public String toString() {
        return toString(this.skillType, this.level);
    }

    public static String toString(SkillType skillType, int level) {
        return Requirement.icon + ChatColor.GREEN + skillType.toString() + " Skill Level " + Functions.integerToRoman(level);
    }
}
