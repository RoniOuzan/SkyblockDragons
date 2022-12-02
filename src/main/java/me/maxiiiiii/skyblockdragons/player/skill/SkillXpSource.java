package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;

@Getter
public class SkillXpSource<T> {
    private final SkillXpSourceType<T> type;
    private final T source;

    public SkillXpSource(SkillXpSourceType<T> type, T source) {
        this.type = type;
        this.source = source;
    }
}
