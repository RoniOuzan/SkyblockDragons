package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;

@Getter
public class StatModifier {
    private final StatModifierType type;
    private final String text;
    private final Stat stat;

    public StatModifier(StatModifierType type, String text, Stat stat) {
        this.type = type;
        this.text = text;
        this.stat = stat;
    }
}
