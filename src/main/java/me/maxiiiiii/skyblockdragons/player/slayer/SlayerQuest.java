package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;

@Getter
public class SlayerQuest {
    private final SlayerType type;
    private final int level;
    private final double currentXp;

    public SlayerQuest(SlayerType type, int level) {
        this.type = type;
        this.level = level;
        this.currentXp = 0;
    }
}
