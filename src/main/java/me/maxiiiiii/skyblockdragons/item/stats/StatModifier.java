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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StatModifier)) return false;

        return this.getStat().getType() == ((StatModifier) other).getStat().getType() &&
                this.getType() == ((StatModifier) other).getType() &&
                this.getText().equals(((StatModifier) other).getText());
    }
}
