package me.maxiiiiii.skyblockdragons.player.slayer;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public class SlayerQuest {
    private final SlayerType type;
    private final double currentXp;

    public SlayerQuest(SlayerType type, double currentXp) {
        this.type = type;
        this.currentXp = currentXp;
    }

    public SlayerQuest(PlayerSD player) {
        this(SlayerType.REVENANT, 0);
    }
}
