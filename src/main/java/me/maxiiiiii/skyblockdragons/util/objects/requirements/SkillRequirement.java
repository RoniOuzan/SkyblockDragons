package me.maxiiiiii.skyblockdragons.util.objects.requirements;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;

public class SkillRequirement extends Requirement {
    public static final SkillRequirement NULL = new SkillRequirement(SkillType.COMBAT, 0);

    private final SkillType skillType;

    public SkillRequirement(SkillType skillType, int level) {
        super(RequirementType.SKILL, level);
        this.skillType = skillType;
    }

    @Override
    public boolean hasRequirement(PlayerSD player) {
        return player.getSkill().get(skillType).getLevel() >= this.level;
    }
}
