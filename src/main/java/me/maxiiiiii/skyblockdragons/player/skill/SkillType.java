package me.maxiiiiii.skyblockdragons.player.skill;

import me.maxiiiiii.skyblockdragons.util.Functions;

public enum SkillType {
    FARMING,
    MINING,
    COMBAT,
    FORAGING,
    FISHING,
    ENCHANTING,
    ALCHEMY,
    TAMING,
    DUNGEONEERING;

    @Override
    public String toString() {
        return Functions.setTitleCase(this.name());
    }
}
