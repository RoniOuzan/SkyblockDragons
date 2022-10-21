package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;

public class SingleCooldown {
    private double startedAt;
    private final double seconds;

    public SingleCooldown(double seconds) {
        this.startedAt = 0;
        this.seconds = seconds;
    }

    public void reset() {
        this.startedAt = SkyblockDragons.getCurrentTimeInSeconds();
    }

    public boolean isExpired() {
        return SkyblockDragons.getCurrentTimeInSeconds() - this.startedAt >= this.seconds;
    }
}
